package ua.footballdata.serviceAPI;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

//@Component
@Service
@Scope("application")
public class APIRequestLimit {
	private static final int FOOTBALL_API_REQUEST_PER_MINUTE_LIMIT = 10;
	private static final int FOOTBALL_API_REQUEST_SECONDS_LIMIT = 60;

	private static final Logger logger = LoggerFactory.getLogger(APIRequestLimit.class);

	// private int requestsCount = FOOTBALL_API_REQUEST_PER_MINUTE_LIMIT;
	private List<Date> requestsList = new ArrayList<>();
	// private Date lastRequest = new Date();

	/*
	 * public APIRequestLimit() { this.requestsCount =
	 * FOOTBALL_API_REQUEST_PER_MINUTE_LIMIT; this.lastRequest = new Date(); }
	 */

	/*
	 * public int getRequestsCount() { return requestsCount; }
	 * 
	 * public void setRequestsCount(int requestsCount) { this.requestsCount =
	 * requestsCount; }
	 * 
	 * public Date getLastRequest() { return lastRequest; }
	 * 
	 * public void setLastRequest(Date lastRequest) { this.lastRequest =
	 * lastRequest; }
	 */

	public void add() {
		/*
		 * if (requestsCount == FOOTBALL_API_REQUEST_PER_MINUTE_LIMIT) { skipData(); }
		 * requestsCount++;
		 */
		if (requestsList.size() == FOOTBALL_API_REQUEST_PER_MINUTE_LIMIT) {
			requestsList.remove(0);
		}
		Date date = new Date();
		clearOld(date);
		requestsList.add(date);

	}

	private void clearOld(Date date) {

		while (requestsList.size() > 0
				&& secondsBetween(requestsList.get(0), date) > FOOTBALL_API_REQUEST_SECONDS_LIMIT) {
			requestsList.remove(0);
		}

	}

	public boolean requestAllowed() {
		long seconds = secondsAfterLastRequest();
		if (requestsList.size() >= FOOTBALL_API_REQUEST_PER_MINUTE_LIMIT
				&& seconds <= FOOTBALL_API_REQUEST_SECONDS_LIMIT) {
			return false;
		}
		return true;
	}

	public long secondsAfterLastRequest() {
		/*
		 * if (lastRequest == null) { lastRequest = new Date(); // return 61; } long
		 * seconds = ((new Date()).getTime() - lastRequest.getTime()) / 1000;
		 */
		if (requestsList.size() == 0) {
			requestsList.add(new Date());

		}

		long seconds = secondsBetween(requestsList.get(0), new Date());
		return seconds;
	}

	private long secondsBetween(Date dateFrom, Date dateTill) {
		return (dateTill.getTime() - dateFrom.getTime()) / 1000;
	}

	/*
	 * public void skipData() { this.requestsCount = 0; this.lastRequest = new
	 * Date(); }
	 */

	public void checkAndWait() {
		if (!requestAllowed()) {
			try {
				/*
				 * long x = (FOOTBALL_API_REQUEST_PER_MINUTE_LIMIT - requestsList.size())
				 * (FOOTBALL_API_REQUEST_SECONDS_LIMIT / FOOTBALL_API_REQUEST_PER_MINUTE_LIMIT)
				 * + 1;
				 */

				long timeForWaiting = FOOTBALL_API_REQUEST_SECONDS_LIMIT
						- secondsBetween(requestsList.get(0), new Date());
				if (timeForWaiting < 0) {
					timeForWaiting = 0;
				}
				// Thread.sleep(timeForWaiting * 1000 + 100);
				getTimer(timeForWaiting * 1000 + 100).call();
				// Thread.sleep(secondsAfterLastRequest() * 1000);
			} catch (InterruptedException e) {
				logger.error("Waiting error: " + e.getMessage());
				e.printStackTrace();
			} catch (Exception e) {
				logger.error("Waiting error: " + e.getMessage());
				e.printStackTrace();
			}

		}
		add();
	}

	/*
	 * In cause disconnect by timeout:
	 * https://stackoverflow.com/questions/51066238/spring-server-connection-timeout
	 * -not-working
	 */
	private Callable<String> getTimer(long time) throws InterruptedException {
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(time); // this will cause a timeout
				return "Timer woke up";
			}
		};
	}
}

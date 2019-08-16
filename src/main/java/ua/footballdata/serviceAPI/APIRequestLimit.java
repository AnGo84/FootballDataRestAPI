package ua.footballdata.serviceAPI;

import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

/*@Component
//@Service
@Scope("application")*/
public class APIRequestLimit {
	private static final Logger logger = LoggerFactory.getLogger(AreaAppServiceImp.class);

	@Value("${footballdata.response.headers.second_left_to_reset}")
	private String headerRequestCounterReset;
	@Value("${footballdata.response.headers.remaining_requests}")
	private String headerRequestsAvailableMinute;

	private int secondsToReset;
	private int requestsAvailableInMinute;

	public void initByHeaders(HttpHeaders headers) {
		secondsToReset = -1;
		requestsAvailableInMinute = -1;
		if (headers != null) {
			secondsToReset = getIntValueByName(headers, headerRequestCounterReset);
			requestsAvailableInMinute = getIntValueByName(headers, headerRequestsAvailableMinute);
		}
	}

	private int getIntValueByName(HttpHeaders headers, String headerName) {
		int intValue = -1;

		if (headers != null && headers.get(headerName) != null && !headers.get(headerName).isEmpty()) {
			String headerValue = headers.get(headerName).get(0);
			if (headerValue != null && !headerValue.equals("")) {
				try {
					intValue = Integer.valueOf(headerValue);
				} catch (Exception e) {
					logger.error("Cannot get int value from " + headerValue);
					checkHeaders(headers);
				}
			}
		}
		return intValue;
	}

	public APIRequestLimit(int secondsToReset, int requestsAvailableInMinute) {
		super();
		this.secondsToReset = secondsToReset;
		this.requestsAvailableInMinute = requestsAvailableInMinute;
	}

	public APIRequestLimit() {
		secondsToReset = -1;
		requestsAvailableInMinute = -1;
	}

	public void reset() {
		secondsToReset = -1;
		requestsAvailableInMinute = -1;
	}

	private void checkHeaders(HttpHeaders headers) {
		// indicates the version you are using
		// String requestsAPIVersion =
		// String.valueOf(headers.get("X-API-Version").get(0));
		// Shows the detected API-client or 'anonymous'
		// String requestsClient =
		// String.valueOf(headers.get("X-Authenticated-Client").get(0));

		// Defines the seconds left to reset your request counter.
		// int requestsSecondsToReset =
		// Integer.valueOf(headers.get("X-RequestCounter-Reset").get(0));
		// Shows the remaining requests before being blocked.
		// int requestsAvailable =
		// Integer.valueOf(headers.get("X-Requests-Available-Minute").get(0));

		// String requestsAvailable =
		// String.valueOf(headers.get("X-Requests-Available-Minute").get(0));

		/*
		 * logger.info("X-API-Version: " + requestsAPIVersion);
		 * logger.info("X-Authenticated-Client: " + requestsClient);
		 * logger.info("X-RequestCounter-Reset: " + requestsSecondsToReset);
		 * logger.info("X-Requests-Available-Minute: " + requestsSecondsToReset);
		 */

		for (Entry<String, List<String>> element : headers.entrySet()) {
			if (element != null) {
				logger.info("Header: " + element.getKey());
				if (element.getValue() != null && !element.getValue().isEmpty()) {
					for (String headerElement : element.getValue()) {
						logger.info("	Element: " + headerElement);
					}
				}
			}
		}

	}

	public int getSecondsToReset() {
		return secondsToReset;
	}

	public void setSecondsToReset(int secondsToReset) {
		this.secondsToReset = secondsToReset;
	}

	public int getRequestsAvailableInMinute() {
		return requestsAvailableInMinute;
	}

	public void setRequestsAvailableInMinute(int requestsAvailableInMinute) {
		this.requestsAvailableInMinute = requestsAvailableInMinute;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + requestsAvailableInMinute;
		result = prime * result + secondsToReset;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		APIRequestLimit other = (APIRequestLimit) obj;
		if (requestsAvailableInMinute != other.requestsAvailableInMinute)
			return false;
		if (secondsToReset != other.secondsToReset)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("APIRequestLimit [headerRequestCounterReset=");
		builder.append(headerRequestCounterReset);
		builder.append(", headerRequestsAvailableMinute=");
		builder.append(headerRequestsAvailableMinute);
		builder.append(", secondsToReset=");
		builder.append(secondsToReset);
		builder.append(", requestsAvailableInMinute=");
		builder.append(requestsAvailableInMinute);
		builder.append("]");
		return builder.toString();
	}

}

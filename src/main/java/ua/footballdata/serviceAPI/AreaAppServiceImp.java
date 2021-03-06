package ua.footballdata.serviceAPI;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ua.footballdata.model.Area;
import ua.footballdata.restservice.AreaRestServiceImpl;

@Service("areaAPIService")
public class AreaAppServiceImp implements AppService<Area> {
	private static final Logger logger = LoggerFactory.getLogger(AreaAppServiceImp.class);

	/*@Value("${footballdata.token}")
	private String token;*/

	@Autowired
	private APIRequestLimitCheck requestLimitCheck;
	@Autowired
	private AreaRestServiceImpl restService;

	public AreaAppServiceImp() {
	}

	/*public AreaAppServiceImp(String token) {
		logger.info("Token for set: {}", token);
		this.token = token;
		this.restService = new AreaRestServiceImpl(token);
	}*/

	@Override
	public Area findById(long id) {
		logger.info("Id {}", id);
		logger.info("restService is null: {}", restService == null);
		/*logger.info("Token for set: {}", token);
		restService = new AreaRestServiceImpl(token);*/

		/*
		 * Competition competition = new Competition(); competition.setId(id);
		 * competition.setCode("Code: " + String.valueOf(id)); return competition;
		 */
		Area area = restService.findById(id);
		requestLimitCheck.checkAndWait(restService.getApiRequestLimit());
		return area;
	}

	@Override
	public List<Area> findAllData() {
		logger.info("All competitions");

		List<Area> list = restService.findAllData();
		requestLimitCheck.checkAndWait(restService.getApiRequestLimit());
		return list;
	}

}

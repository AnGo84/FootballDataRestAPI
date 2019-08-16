package ua.footballdata.serviceAPI;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ua.footballdata.model.Competition;
import ua.footballdata.restservice.CompetitionRestServiceImpl;

@Service("competitionService")
public class CompetitionAppServiceImp implements AppService<Competition> {
	private static final Logger logger = LoggerFactory.getLogger(CompetitionAppServiceImp.class);

	/*@Value("${footballdata.token}")
	private String token;*/

	@Autowired
	private APIRequestLimitCheck requestLimitCheck;

	// TODO https://www.baeldung.com/spring-autowire
	// apply @Autowired for constructor with params
	@Autowired
	private CompetitionRestServiceImpl restService;

	/*public CompetitionAppServiceImp() {

	}

	public CompetitionAppServiceImp(String token) {
		logger.info("Token for set: {}", token);
		this.token = token;
		this.restService = new CompetitionRestServiceImpl(token);
	}*/

	@Override
	public Competition findById(long id) {
		logger.info("Id {}", id);
		logger.info("restService is null: {}", restService == null);
		/*logger.info("Token for set: {}", token);
		restService = new CompetitionRestServiceImpl(token);*/

		/*
		 * Competition competition = new Competition(); competition.setId(id);
		 * competition.setCode("Code: " + String.valueOf(id)); return competition;
		 */
		Competition competition = restService.findById(id);
		requestLimitCheck.checkAndWait(restService.getApiRequestLimit());
		return competition;
	}

	@Override
	public List<Competition> findAllData() {
		logger.info("All competitions");
		List<Competition> list = restService.findAllData();
		requestLimitCheck.checkAndWait(restService.getApiRequestLimit());
		return list;
	}

}

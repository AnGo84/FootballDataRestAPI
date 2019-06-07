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

	@Value("${footballdata.token}")
	private String token;

	@Autowired
	private APIRequestLimit requestLimit;
	// TODO https://www.baeldung.com/spring-autowire
	// apply @Autowired for constructor with params
	private CompetitionRestServiceImpl restService;

	public CompetitionAppServiceImp() {

	}

	public CompetitionAppServiceImp(String token) {
		logger.info("Token for set: {}", token);
		this.token = token;
		this.restService = new CompetitionRestServiceImpl(token);
	}

	@Override
	public Competition findById(long id) {
		logger.info("Id {}", id);
		logger.info("restService is null: {}", restService == null);
		logger.info("Token for set: {}", token);
		restService = new CompetitionRestServiceImpl(token);

		/*
		 * Competition competition = new Competition(); competition.setId(id);
		 * competition.setCode("Code: " + String.valueOf(id)); return competition;
		 */
		requestLimit.checkAndWait();
		return restService.findById(id);
	}

	@Override
	public List<Competition> findAllData() {
		logger.info("All competitions");
		requestLimit.checkAndWait();
		restService = new CompetitionRestServiceImpl();
		return restService.findAllData();
	}

}

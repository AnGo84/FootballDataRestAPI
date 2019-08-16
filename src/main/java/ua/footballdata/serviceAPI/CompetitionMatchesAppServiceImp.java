package ua.footballdata.serviceAPI;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ua.footballdata.model.CompetitionMatches;
import ua.footballdata.restservice.CompetitionMatchesRestServiceImpl;

@Service("competitionMatchesAPIService")
public class CompetitionMatchesAppServiceImp implements AppService<CompetitionMatches> {
	private static final Logger logger = LoggerFactory.getLogger(CompetitionMatchesAppServiceImp.class);

	@Value("${footballdata.token}")
	private String token;

	@Autowired
	private APIRequestLimitCheck requestLimitCheck;

	private CompetitionMatchesRestServiceImpl restService;

	public CompetitionMatchesAppServiceImp() {

	}

	public CompetitionMatchesAppServiceImp(String token) {
		logger.info("Token for set: {}", token);
		this.token = token;
		this.restService = new CompetitionMatchesRestServiceImpl(token);
	}

	@Override
	public CompetitionMatches findById(long id) {
		logger.info("Id {}", id);
		logger.info("restService is null: {}", restService == null);
		logger.info("Token for set: {}", token);
		restService = new CompetitionMatchesRestServiceImpl(token);

		/*
		 * Competition competition = new Competition(); competition.setId(id);
		 * competition.setCode("Code: " + String.valueOf(id)); return competition;
		 */
		CompetitionMatches competitionMatches = restService.findById(id);
		requestLimitCheck.checkAndWait(restService.getApiRequestLimit());
		return competitionMatches;
	}

	public CompetitionMatches findCompetitionMatchesForSeason(long id, int year) {
		logger.info("Id {}, year {}", id, year);
		logger.info("restService is null: {}", restService == null);
		logger.info("Token for set: {}", token);
		restService = new CompetitionMatchesRestServiceImpl(token);
		CompetitionMatches competitionMatches = restService.findCompetitionMatchesForSeason(id, year);
		requestLimitCheck.checkAndWait(restService.getApiRequestLimit());
		return competitionMatches;
	}

	@Override
	public List<CompetitionMatches> findAllData() {
		// TODO Auto-generated method stub
		// requestLimitCheck.checkAndWait(apiRequestLimit);
		return null;
	}

}

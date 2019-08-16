package ua.footballdata.serviceAPI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.footballdata.model.CompetitionMatches;
import ua.footballdata.model.CompetitionTeams;
import ua.footballdata.restservice.CompetitionMatchesRestServiceImpl;
import ua.footballdata.restservice.CompetitionTeamsRestServiceImpl;

import java.util.List;

@Service("competitionTeamsAPIService")
public class CompetitionTeamsAppServiceImp implements AppService<CompetitionTeams> {
	private static final Logger logger = LoggerFactory.getLogger(CompetitionTeamsAppServiceImp.class);

	/*@Value("${footballdata.token}")
	private String token;*/

	@Autowired
	private APIRequestLimitCheck requestLimitCheck;
	@Autowired
	private CompetitionTeamsRestServiceImpl restService;

	/*public CompetitionMatchesAppServiceImp() {

	}*/

	/*public CompetitionMatchesAppServiceImp(String token) {
		logger.info("Token for set: {}", token);
		this.token = token;
		this.restService = new CompetitionMatchesRestServiceImpl(token);
	}*/

	@Override
	public CompetitionTeams findById(long id) {
		logger.info("Id {}", id);
		logger.info("restService is null: {}", restService == null);
		/*logger.info("Token for set: {}", token);
		restService = new CompetitionMatchesRestServiceImpl(token);*/

		/*
		 * Competition competition = new Competition(); competition.setId(id);
		 * competition.setCode("Code: " + String.valueOf(id)); return competition;
		 */
		CompetitionTeams competitionMatches = restService.findById(id);
		requestLimitCheck.checkAndWait(restService.getApiRequestLimit());
		return competitionMatches;
	}

	public CompetitionTeams findCompetitionTeamsForSeason(long id, int year) {
		logger.info("Id {}, year {}", id, year);
		logger.info("restService is null: {}", restService == null);
		/*logger.info("Token for set: {}", token);
		restService = new CompetitionMatchesRestServiceImpl(token);*/
		CompetitionTeams competitionTeams = restService.findCompetitionTeamsForSeason(id, year);
		requestLimitCheck.checkAndWait(restService.getApiRequestLimit());
		return competitionTeams;
	}

	@Override
	public List<CompetitionTeams> findAllData() {
		// TODO Auto-generated method stub
		// requestLimitCheck.checkAndWait(apiRequestLimit);
		return null;
	}

}

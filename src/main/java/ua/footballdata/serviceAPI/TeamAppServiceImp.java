package ua.footballdata.serviceAPI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.footballdata.model.CompetitionTeams;
import ua.footballdata.model.Team;
import ua.footballdata.restservice.TeamRestServiceImpl;

import java.util.List;

@Service("teamAPIService")
public class TeamAppServiceImp implements AppService<Team> {
    private static final Logger logger = LoggerFactory.getLogger(TeamAppServiceImp.class);

	/*@Value("${footballdata.token}")
	private String token;*/

    @Autowired
    private APIRequestLimitCheck requestLimitCheck;
    @Autowired
    private TeamRestServiceImpl restService;

	/*public TeamAppServiceImp() {

	}

	public TeamAppServiceImp(String token) {
		logger.info("Token for set: {}", token);
		this.token = token;
		this.restService = new TeamRestServiceImpl(token);
	}*/

    @Override
    public Team findById(long id) {
        logger.info("Id {}", id);
        logger.info("restService is null: {}", restService == null);
		/*logger.info("Token for set: {}", token);
		restService = new TeamRestServiceImpl(token);*/

        /*
         * Competition competition = new Competition(); competition.setId(id);
         * competition.setCode("Code: " + String.valueOf(id)); return competition;
         */
        Team team = restService.findById(id);
        requestLimitCheck.checkAndWait(restService.getApiRequestLimit());

        return team;
    }

    @Override
    public List<Team> findAllData() {
        // TODO Auto-generated method stub
        // requestLimitCheck.checkAndWait(apiRequestLimit);
        return null;
    }

}

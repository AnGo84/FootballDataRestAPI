package ua.footballdata.serviceAPI;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ua.footballdata.model.Competition;
import ua.footballdata.model.Team;
import ua.footballdata.restservice.CompetitionRestServiceImpl;
import ua.footballdata.restservice.TeamRestServiceImpl;

@Service("teamService")
public class TeamAppServiceImp implements AppService<Team>{
	private static final Logger logger = LoggerFactory.getLogger(TeamAppServiceImp.class);
	
	@Value("${footballdata.token}")
	private String token;
	
	private TeamRestServiceImpl restService;
	
	public TeamAppServiceImp() {
		
	}

	public TeamAppServiceImp(String token) {
		logger.info("Token for set: {}", token);
		this.token = token;
		this.restService = new TeamRestServiceImpl(token);
	}
	
	@Override
	public Team findById(long id) {
		logger.info("Id {}", id);
		logger.info("restService is null: {}", restService==null);
		logger.info("Token for set: {}", token);
		restService = new TeamRestServiceImpl(token);
		
		/*Competition competition = new Competition();
		competition.setId(id);
		competition.setCode("Code: " + String.valueOf(id));
		return competition;*/
		
		return restService.findById(id);
	}

	@Override
	public List<Team> findAllData() {
		// TODO Auto-generated method stub
		return null;
	}

}

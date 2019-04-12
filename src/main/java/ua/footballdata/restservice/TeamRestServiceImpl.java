package ua.footballdata.restservice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import ua.footballdata.model.Competition;
import ua.footballdata.model.Team;

public class TeamRestServiceImpl extends AbstractRestService<Team> {
	private static final Logger logger = LoggerFactory.getLogger(TeamRestServiceImpl.class);
	
	public TeamRestServiceImpl(String token) {
		super(token);
		
		logger.info("Token: " + this.getToken());
		logger.info("Headers: " + this.getHttpEntitis());
		
	}

	@Override
	public Team findById(long id) {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Team> respEntity = restTemplate.exchange(
				"http://api.football-data.org/v2/teams/" + id, HttpMethod.GET, this.getHttpEntitis(),
				Team.class);
		logger.info("Get respEntity is null: " + (respEntity==null));
		Team team = respEntity.getBody();

		// Competition competition =
		// restTemplate.getForObject("http://api.football-data.org/v2/competitions/" +
		// id, this.getHttpEntitis(), Competition.class);

		return team;
	}

	@Override
	public List<Team> findAllData() {
		// TODO Auto-generated method stub
		return null;
	}

}

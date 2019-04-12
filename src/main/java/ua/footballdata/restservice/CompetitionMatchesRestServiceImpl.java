package ua.footballdata.restservice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import ua.footballdata.model.Competition;
import ua.footballdata.model.CompetitionMatches;

public class CompetitionMatchesRestServiceImpl extends AbstractRestService<CompetitionMatches> {
	private static final Logger logger = LoggerFactory.getLogger(CompetitionMatchesRestServiceImpl.class);
	
	public CompetitionMatchesRestServiceImpl(String token) {
		super(token);
		
		logger.info("Token: " + this.getToken());
		logger.info("Headers: " + this.getHttpEntitis());
		
	}

	@Override
	public CompetitionMatches findById(long id) {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<CompetitionMatches> respEntity = restTemplate.exchange(
				"http://api.football-data.org/v2/competitions/" + id+"/matches", HttpMethod.GET, this.getHttpEntitis(),
				CompetitionMatches.class);
		logger.info("Get respEntity is null: " + (respEntity==null));
		CompetitionMatches competitionMatches = respEntity.getBody();

		// Competition competition =
		// restTemplate.getForObject("http://api.football-data.org/v2/competitions/" +
		// id, this.getHttpEntitis(), Competition.class);

		return competitionMatches;
	}

	@Override
	public List<CompetitionMatches> findAllData() {
		// TODO Auto-generated method stub
		return null;
	}

}

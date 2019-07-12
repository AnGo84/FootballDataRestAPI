package ua.footballdata.restservice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import ua.footballdata.model.Competition;
import ua.footballdata.model.Competitions;

public class CompetitionRestServiceImpl extends AbstractRestService<Competition> {
	private static final Logger logger = LoggerFactory.getLogger(CompetitionRestServiceImpl.class);

	public CompetitionRestServiceImpl() {
	}

	public CompetitionRestServiceImpl(String token) {
		super(token);

		logger.info("Token: " + this.getToken());
		logger.info("Headers: " + this.getHttpEntitis());

	}

	@Override
	public Competition findById(long id) {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Competition> respEntity = restTemplate.exchange(
				"http://api.football-data.org/v2/competitions/" + id, HttpMethod.GET, this.getHttpEntitis(),
				Competition.class);
		logger.info("Get respEntity is null: " + (respEntity == null));
		Competition competition = respEntity.getBody();

		// Competition competition =
		// restTemplate.getForObject("http://api.football-data.org/v2/competitions/" +
		// id, this.getHttpEntitis(), Competition.class);

		return competition;
	}

	@Override
	public List<Competition> findAllData() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Competitions> respEntity = restTemplate.exchange("http://api.football-data.org/v2/competitions",
				HttpMethod.GET, this.getHttpEntitis(), Competitions.class);
		logger.info("Get respEntity is null: " + (respEntity == null));
		Competitions competitions = respEntity.getBody();
		return competitions.getCompetitions();
	}

}

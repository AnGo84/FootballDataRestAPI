package ua.footballdata.restservice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ua.footballdata.model.Competition;
import ua.footballdata.model.Competitions;
import ua.footballdata.serviceAPI.APIRequestLimit;
@Service
public class CompetitionRestServiceImpl extends AbstractRestService<Competition> {
	private static final Logger logger = LoggerFactory.getLogger(CompetitionRestServiceImpl.class);
	@Autowired
	private APIRequestLimit apiRequestLimit;

	/*public CompetitionRestServiceImpl() {
		apiRequestLimit = new APIRequestLimit();
	}*/

	public CompetitionRestServiceImpl(@Value("${footballdata.token}") String token) {
		super(token);
		apiRequestLimit = new APIRequestLimit();
		logger.info("Token: " + this.getToken());
		logger.info("Headers: " + this.getHttpEntitis());
	}

	public APIRequestLimit getApiRequestLimit() {
		return apiRequestLimit;
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

		apiRequestLimit.initByHeaders(respEntity.getHeaders());
		return competition;
	}

	@Override
	public List<Competition> findAllData() {
		RestTemplate restTemplate = new RestTemplate();

		/*ResponseEntity<Competitions> respEntity = restTemplate.exchange("http://api.football-data.org/v2/competitions",
				HttpMethod.GET, this.getHttpEntitis(), Competitions.class);*/
		ResponseEntity<Competitions> respEntity = restTemplate.exchange("http://api.football-data.org/v2/competitions",
				HttpMethod.GET, this.getHttpEntitis(), Competitions.class);
		logger.info("Get respEntity is null: " + (respEntity == null));

		apiRequestLimit.initByHeaders(respEntity.getHeaders());

		Competitions competitions = respEntity.getBody();
		return competitions.getCompetitions();
	}

}

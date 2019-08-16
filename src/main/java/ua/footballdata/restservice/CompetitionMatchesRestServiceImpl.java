package ua.footballdata.restservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.footballdata.model.CompetitionMatches;
import ua.footballdata.serviceAPI.APIRequestLimit;

import java.util.List;

@Service
public class CompetitionMatchesRestServiceImpl extends AbstractRestService<CompetitionMatches> {
    private static final Logger logger = LoggerFactory.getLogger(CompetitionMatchesRestServiceImpl.class);
    @Autowired
    private APIRequestLimit apiRequestLimit;

    public CompetitionMatchesRestServiceImpl(@Value("${footballdata.token}") String token) {
        super(token);
        apiRequestLimit = new APIRequestLimit();
        logger.info("Token: " + this.getToken());
        logger.info("Headers: " + this.getHttpEntitis());

    }

    public APIRequestLimit getApiRequestLimit() {
        return apiRequestLimit;
    }

    @Override
    public CompetitionMatches findById(long id) {
        logger.info("Get matches for competition id " + id);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CompetitionMatches> respEntity = restTemplate.exchange(
                "http://api.football-data.org/v2/competitions/" + id + "/matches", HttpMethod.GET,
                this.getHttpEntitis(), CompetitionMatches.class);
        logger.info("Get respEntity is null: " + (respEntity == null));

        apiRequestLimit.initByHeaders(respEntity.getHeaders());

        CompetitionMatches competitionMatches = respEntity.getBody();

        // Competition competition =
        // restTemplate.getForObject("http://api.football-data.org/v2/competitions/" +
        // id, this.getHttpEntitis(), Competition.class);

        return competitionMatches;
    }

    public CompetitionMatches findCompetitionMatchesForSeason(long id, int year) {
        logger.info("Get matches for competition id " + id + " and season year " + year);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CompetitionMatches> respEntity = restTemplate.exchange(
                "http://api.football-data.org/v2/competitions/" + id + "/matches?season=" + year, HttpMethod.GET,
                this.getHttpEntitis(), CompetitionMatches.class);
        logger.info("Get respEntity is null: " + (respEntity == null));

        apiRequestLimit.initByHeaders(respEntity.getHeaders());

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

package ua.footballdata.restservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.footballdata.model.CompetitionMatches;
import ua.footballdata.model.CompetitionTeams;
import ua.footballdata.serviceAPI.APIRequestLimit;

import java.util.List;

@Service
public class CompetitionTeamsRestServiceImpl extends AbstractRestService<CompetitionTeams> {
    private static final Logger logger = LoggerFactory.getLogger(CompetitionTeamsRestServiceImpl.class);
    @Autowired
    private APIRequestLimit apiRequestLimit;

    public CompetitionTeamsRestServiceImpl(@Value("${footballdata.token}") String token) {
        super(token);
        apiRequestLimit = new APIRequestLimit();
        logger.info("Token: " + this.getToken());
        logger.info("Headers: " + this.getHttpEntitis());

    }

    public APIRequestLimit getApiRequestLimit() {
        return apiRequestLimit;
    }

    @Override
    public CompetitionTeams findById(long id) {
        logger.info("Get teams for competition id " + id);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CompetitionTeams> respEntity = restTemplate.exchange(
                "http://api.football-data.org/v2/competitions/" + id + "/teams", HttpMethod.GET,
                this.getHttpEntitis(), CompetitionTeams.class);
        logger.info("Get respEntity is null: " + (respEntity == null));

        apiRequestLimit.initByHeaders(respEntity.getHeaders());

        CompetitionTeams competitionTeams = respEntity.getBody();
        return competitionTeams;
    }


    public CompetitionTeams findCompetitionTeamsForSeason(long id, int year) {
        logger.info("Find Competition " +id+ " Teams For Season "+ year);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CompetitionTeams> respEntity = restTemplate.exchange(
                "http://api.football-data.org/v2/competitions/" + id + "/teams?season=" + year,
                HttpMethod.GET, this.getHttpEntitis(), CompetitionTeams.class);
        logger.info("Get respEntity is null: " + (respEntity == null));

        apiRequestLimit.initByHeaders(respEntity.getHeaders());

        CompetitionTeams list = respEntity.getBody();
        return list;
    }

    @Override
    public List<CompetitionTeams> findAllData() {
        // TODO Auto-generated method stub
        return null;
    }

}

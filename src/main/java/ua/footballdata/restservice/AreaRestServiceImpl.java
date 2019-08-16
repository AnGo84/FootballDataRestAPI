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
import ua.footballdata.model.Area;
import ua.footballdata.model.Competitions;
import ua.footballdata.serviceAPI.APIRequestLimit;

import java.util.List;

@Service
public class AreaRestServiceImpl extends AbstractRestService<Area> {
    private static final Logger logger = LoggerFactory.getLogger(AreaRestServiceImpl.class);
    @Autowired
    private APIRequestLimit apiRequestLimit;

    public AreaRestServiceImpl(@Value("${footballdata.token}") String token) {
        super(token);
        apiRequestLimit = new APIRequestLimit();
        logger.info("Token: " + this.getToken());
        logger.info("Headers: " + this.getHttpEntitis());

    }

    @Override
    public Area findById(long id) {
        logger.info("Find Area by id: " + id);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Area> respEntity = restTemplate.exchange("http://api.football-data.org/v2/areas/" + id,
                HttpMethod.GET, this.getHttpEntitis(), Area.class);

        logger.info("Get respEntity is null: " + (respEntity == null));

        apiRequestLimit.initByHeaders(respEntity.getHeaders());

        Area area = respEntity.getBody();

        // Competition competition =
        // restTemplate.getForObject("http://api.football-data.org/v2/competitions/" +
        // id, this.getHttpEntitis(), Competition.class);

        return area;
    }

    public APIRequestLimit getApiRequestLimit() {
        return apiRequestLimit;
    }

    @Override
    public List<Area> findAllData() {
        logger.info("Find All Areas");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Area>> respEntity = restTemplate.exchange("http://api.football-data.org/v2/areas",
                HttpMethod.GET, this.getHttpEntitis(), new ParameterizedTypeReference<List<Area>>(){});
        logger.info("Get respEntity is null: " + (respEntity == null));

        apiRequestLimit.initByHeaders(respEntity.getHeaders());

        List<Area> areas = respEntity.getBody();
        return areas;
    }

}

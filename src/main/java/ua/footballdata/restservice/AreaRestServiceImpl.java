package ua.footballdata.restservice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import ua.footballdata.model.Area;

public class AreaRestServiceImpl extends AbstractRestService<Area> {
	private static final Logger logger = LoggerFactory.getLogger(AreaRestServiceImpl.class);

	public AreaRestServiceImpl(String token) {
		super(token);

		logger.info("Token: " + this.getToken());
		logger.info("Headers: " + this.getHttpEntitis());

	}

	@Override
	public Area findById(long id) {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Area> respEntity = restTemplate.exchange("http://api.football-data.org/v2/areas/" + id,
				HttpMethod.GET, this.getHttpEntitis(), Area.class);

		logger.info("Get respEntity is null: " + (respEntity == null));
		Area area = respEntity.getBody();

		// Competition competition =
		// restTemplate.getForObject("http://api.football-data.org/v2/competitions/" +
		// id, this.getHttpEntitis(), Competition.class);

		return area;
	}

	@Override
	public List<Area> findAllData() {
		// TODO Auto-generated method stub
		return null;
	}

}

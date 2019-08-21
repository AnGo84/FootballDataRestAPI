package ua.footballdata.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.footballdata.error.CustomErrorType;
import ua.footballdata.model.Area;
import ua.footballdata.model.entity.AreaEntity;
import ua.footballdata.model.mapper.AreaMapper;
import ua.footballdata.service.AreaEntityServiceImpl;
import ua.footballdata.service.UpdateDynamoDBFromAPIService;
import ua.footballdata.serviceAPI.AreaAppServiceImp;

@RestController
@RequestMapping("/areas")
public class AreaController {

	public static final Logger logger = LoggerFactory.getLogger(AreaController.class);

	/*
	 * @Value("${footballdata.token}") private String token;
	 */

	@Autowired
	private AreaEntityServiceImpl entityService;
	@Autowired
	private AreaAppServiceImp appAPIService;
	@Autowired
	private UpdateDynamoDBFromAPIService updateFromAPIService;

	//
	@Autowired
	private AreaMapper mapper;

	// ----------- Retrieve All CompetitionEntities-------------------------------

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public ResponseEntity<List<AreaEntity>> listAllCompetitions() {
		List<AreaEntity> entitiesList = entityService.findAll();
		if (entitiesList.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<AreaEntity>>(entitiesList, HttpStatus.OK);
	}

	// ------------ Retrieve Single AreaEntity-------------------------------

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCompetition(@PathVariable("id") long id) {
		logger.info("Fetching CompetitionEntity with id {}", id);
		AreaEntity entity = null;
		try {
			entity = entityService.findById(id);

			if (entity == null) {
				logger.error("AreaEntity with id {} not found.", id);
				return new ResponseEntity(new CustomErrorType("CompetitionEntity with id " + id + " not found"),
						HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<AreaEntity>(entity, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Getting data for Area {} error.", id, e);
			return new ResponseEntity(new CustomErrorType("Getting data for Area " + id + " error. " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	// -------------------Create a AreaEntity ------------------------------

	/*
	 * @RequestMapping(value = "/add", method = RequestMethod.POST) public
	 * ResponseEntity<?> createUser(@RequestBody CompetitionEntity
	 * competitionEntity, UriComponentsBuilder ucBuilder) {
	 * logger.info("Creating CompetitionEntity : {}", competitionEntity);
	 * 
	 * if (entityService.isExist(competitionEntity)) {
	 * logger.error("Unable to create. A Competition with Id {} already exist",
	 * competitionEntity.getId()); return new ResponseEntity( new CustomErrorType(
	 * "Unable to create. A Competition with id " + competitionEntity.getId() +
	 * " already exist."), HttpStatus.CONFLICT); }
	 * entityService.save(competitionEntity);
	 * 
	 * HttpHeaders headers = new HttpHeaders();
	 * headers.setLocation(ucBuilder.path("/competitions/{id}").buildAndExpand(
	 * competitionEntity.getId()).toUri()); return new
	 * ResponseEntity<String>(headers, HttpStatus.CREATED); }
	 */

	// ------------------- Update a AreaEntity ------------------------

	/*
	 * @RequestMapping(value = "/update", method = RequestMethod.PUT) public
	 * ResponseEntity<?> update(@RequestBody CompetitionEntity competitionEntity) {
	 * logger.info("Updating CompetitionEntity with id {}",
	 * competitionEntity.getId());
	 * 
	 * CompetitionEntity currentCompetitionEntity =
	 * entityService.findById(competitionEntity.getId());
	 * 
	 * if (currentCompetitionEntity == null) {
	 * logger.error("Unable to update. CompetitionEntity with id {} not found.",
	 * competitionEntity.getId()); return new ResponseEntity( new CustomErrorType(
	 * "Unable to upate. Competition with id " + competitionEntity.getId() +
	 * " not found."), HttpStatus.NOT_FOUND); }
	 * 
	 * entityService.update(currentCompetitionEntity); return new
	 * ResponseEntity<CompetitionEntity>(currentCompetitionEntity, HttpStatus.OK); }
	 */

	// --------------- Delete a AreaEntity ---------------------------------

	/*
	 * @RequestMapping(value = "/delete-{id}", method = RequestMethod.DELETE) public
	 * ResponseEntity<?> delete(@PathVariable("id") long id) {
	 * logger.info("Fetching & Deleting CompetitionEntity with id {}", id);
	 * 
	 * CompetitionEntity user = entityService.findById(id); if (user == null) {
	 * logger.error("Unable to delete. CompetitionEntity with id {} not found.",
	 * id); return new ResponseEntity( new
	 * CustomErrorType("Unable to delete. Competition with id " + id +
	 * " not found."), HttpStatus.NOT_FOUND); } entityService.deleteById(id); return
	 * new ResponseEntity<CompetitionEntity>(HttpStatus.NO_CONTENT); }
	 */

	// ------------ Update all AreaEntity from API --------------------

	@RequestMapping(value = { "/updatefromapi", "/updatefromapi/" }, method = RequestMethod.GET)
	public ResponseEntity<?> updateAreasFromAPI() {
		logger.info("Update Areas from API");

		List<Area> areas = appAPIService.findAllData();

		if (areas == null || areas.isEmpty()) {
			logger.error("Areas not found.");
			return new ResponseEntity(new CustomErrorType("Areas not found"), HttpStatus.NOT_FOUND);
		}

		List<AreaEntity> areaEntities = new ArrayList<AreaEntity>();
		for (Area area : areas) {
			AreaEntity areaEntity = mapper.convertToEntity(area);
			areaEntities.add(areaEntity);
		}

		try {
			updateFromAPIService.updateAreasList(areaEntities);
		} catch (CustomErrorType e) {
			logger.error("Update Areas list from API error: {}.", e.getMessage());
			return new ResponseEntity(new CustomErrorType("Update Areas list from API error: " + e.getMessage()),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return new ResponseEntity<AppResponse>(new AppResponse("Added Areas list. Size: " + areaEntities.size()),
				HttpStatus.NO_CONTENT);
	}
	// ------------ Update a AreaEntity from API --------------------

	/*
	 * @RequestMapping(value = "/updatefromapi/{id}", method = RequestMethod.GET)
	 * public ResponseEntity<?> getCompetitionFromAPI(@PathVariable("id") long id) {
	 * logger.info("Fetching CompetitionEntity with id {} from API", id);
	 * 
	 * Competition competition = appAPIService.findById(id);
	 * logger.info("Fetching Competition with id {} from API: {}", id, competition);
	 * 
	 * if (competition == null) { logger.error("Competition with id {} not found.",
	 * id); return new ResponseEntity(new CustomErrorType("Competition with id " +
	 * id + " not found"), HttpStatus.NOT_FOUND); }
	 * 
	 * CompetitionEntity competitionEntity = mapper.convertToEntity(competition);
	 * logger.info("Fetching CompetitionEntity: ", competitionEntity);
	 * 
	 * try { updateFromAPIService.updateCompetitionWithMatches(id); } catch
	 * (CustomErrorType e) {
	 * logger.error("Update Competition with id {} from API error {}.", id,
	 * e.getMessage()); return new ResponseEntity( new
	 * CustomErrorType("Update Competition with id " + id + " from API error " +
	 * e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY); }
	 * 
	 * return new ResponseEntity<CompetitionEntity>(competitionEntity,
	 * HttpStatus.OK); }
	 */

}

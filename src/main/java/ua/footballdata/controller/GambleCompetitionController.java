package ua.footballdata.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.footballdata.error.CustomErrorType;
import ua.footballdata.model.entity.CompetitionEntity;
import ua.footballdata.model.entity.GambleCompetition;
import ua.footballdata.model.mapper.GambleCompetitionMapper;
import ua.footballdata.service.CompetitionEntityServiceImpl;

@RestController
@RequestMapping("/gamblecompetitions")
public class GambleCompetitionController {

	public static final Logger logger = LoggerFactory.getLogger(GambleCompetitionController.class);

	@Autowired
	private CompetitionEntityServiceImpl competitionService;
	//
	@Autowired
	private GambleCompetitionMapper mapper;

	// ----------- Retrieve All GambleCompetitionEntities--------------------------

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public ResponseEntity<List<GambleCompetition>> listAllGambleCompetitions() {
		logger.error("Get All Active Competitions for Gamble.");
		List<CompetitionEntity> competitionsList = competitionService.findAll();

		if (competitionsList == null || competitionsList.isEmpty()) {
			logger.error("Active App Competitions not found.");
			return new ResponseEntity(new CustomErrorType("Active App Competitions not found"), HttpStatus.NOT_FOUND);
		}

		List<GambleCompetition> gambleCompetitionsList = new ArrayList<>();
		competitionsList.forEach(competition -> gambleCompetitionsList.add(mapper.convertToEntity(competition)));

		/*
		 * for (Area area : areas) { AreaEntity areaEntity =
		 * mapper.convertToEntity(area); gambleUsersList.add(areaEntity); }
		 */

		if (gambleCompetitionsList.isEmpty()) {
			return new ResponseEntity(new CustomErrorType("Convertion Gamble Competitions not found"),
					HttpStatus.NOT_FOUND);
			// return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<GambleCompetition>>(gambleCompetitionsList, HttpStatus.OK);
	}

	// ------------ Retrieve Single GambleUserEntity-------------------------------

	/*
	 * @RequestMapping(value = "/{id}", method = RequestMethod.GET) public
	 * ResponseEntity<?> getGambleUser(@PathVariable("id") long id) {
	 * logger.info("Fetching AreaEntity with id {}", id); AreaEntity entity = null;
	 * try { entity = entityService.findById(id);
	 * 
	 * if (entity == null) { logger.error("AreaEntity with id {} not found.", id);
	 * return new ResponseEntity(new CustomErrorType("AreaEntity with id " + id +
	 * " not found"), HttpStatus.NOT_FOUND); } return new
	 * ResponseEntity<AreaEntity>(entity, HttpStatus.OK); } catch (Exception e) {
	 * logger.error("Getting data for Area {} error.", id, e); return new
	 * ResponseEntity(new CustomErrorType("Getting data for Area " + id + " error. "
	 * + e.getMessage()), HttpStatus.BAD_REQUEST); } }
	 */

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

	/*
	 * @RequestMapping(value = { "/updatefromapi", "/updatefromapi/" }, method =
	 * RequestMethod.GET) public ResponseEntity<?> updateAreasFromAPI() {
	 * logger.info("Update Areas from API");
	 * 
	 * List<Area> areas = appAPIService.findAllData();
	 * 
	 * if (areas == null || areas.isEmpty()) { logger.error("Areas not found.");
	 * return new ResponseEntity(new CustomErrorType("Areas not found"),
	 * HttpStatus.NOT_FOUND); }
	 * 
	 * List<AreaEntity> areaEntities = new ArrayList<AreaEntity>(); for (Area area :
	 * areas) { AreaEntity areaEntity = mapper.convertToEntity(area);
	 * areaEntities.add(areaEntity); }
	 * 
	 * try { updateFromAPIService.updateAreasList(areaEntities); } catch
	 * (CustomErrorType e) { logger.error("Update Areas list from API error: {}.",
	 * e.getMessage()); return new ResponseEntity(new
	 * CustomErrorType("Update Areas list from API error: " + e.getMessage()),
	 * HttpStatus.UNPROCESSABLE_ENTITY); }
	 * 
	 * return new ResponseEntity<AppResponse>(new
	 * AppResponse("Added Areas list. Size: " + areaEntities.size()),
	 * HttpStatus.NO_CONTENT); }
	 */
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

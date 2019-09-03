package ua.footballdata.controller;

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
import ua.footballdata.model.entity.GambleRuleEntity;
import ua.footballdata.service.GambleRuleEntityServiceImpl;

@RestController
@RequestMapping("/gamblerules")
public class GambleRuleController {

	public static final Logger logger = LoggerFactory.getLogger(GambleRuleController.class);

	@Autowired
	private GambleRuleEntityServiceImpl entityService;

	// ----------- Retrieve All GambleRulesEntities-------------------------------

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public ResponseEntity<List<GambleRuleEntity>> listAllGambleRules() {
		List<GambleRuleEntity> entitiesList = entityService.findAll();
		if (entitiesList.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<GambleRuleEntity>>(entitiesList, HttpStatus.OK);
	}

	// ----------- Retrieve All active GambleRulesEntities-------------------------

	@RequestMapping(value = { "/active={active}" }, method = RequestMethod.GET)
	public ResponseEntity<?> listAllGambleRulesByActive(@PathVariable("active") boolean active) {
		logger.info("Fetching GambleRuleEntities with active {}", active);
		List<GambleRuleEntity> entitiesList = entityService.findByActive(active);
		if (entitiesList == null || entitiesList.isEmpty()) {
			logger.info("GambleRuleEntities with active {} is null or empty", active);
			// return new ResponseEntity(HttpStatus.NO_CONTENT);
			return new ResponseEntity(new CustomErrorType("GambleRuleEntities with active '" + active + "' not found"),
					HttpStatus.NOT_FOUND);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<GambleRuleEntity>>(entitiesList, HttpStatus.OK);
	}

	// ------------ Retrieve Single GambleRuleEntity-------------------------------

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getGambleRule(@PathVariable("id") long id) {
		logger.info("Fetching GambleRuleEntity with id {}", id);
		GambleRuleEntity entity = null;
		try {
			entity = entityService.findById(id);

			if (entity == null) {
				logger.error("GambleRuleEntity with id {} not found.", id);
				return new ResponseEntity(new CustomErrorType("GambleRuleEntity with id " + id + " not found"),
						HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<GambleRuleEntity>(entity, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Getting data for GambleRule {} error.", id, e);
			return new ResponseEntity(
					new CustomErrorType("Getting data for GambleRule " + id + " error. " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	// -------------------Create a GambleRuleEntity ------------------------------

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

	// ------------------- Update a GambleRuleEntity ------------------------

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

	// --------------- Delete a GambleRuleEntity ---------------------------------

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

}

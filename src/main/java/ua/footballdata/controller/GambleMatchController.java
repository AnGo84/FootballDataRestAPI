package ua.footballdata.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ua.footballdata.error.CustomErrorType;
import ua.footballdata.model.entity.GambleMatchEntity;
import ua.footballdata.service.GambleMatchEntityServiceImpl;

@RestController
@RequestMapping("/gamblematches")
public class GambleMatchController {

	public static final Logger logger = LoggerFactory.getLogger(GambleMatchController.class);

	@Autowired
	private GambleMatchEntityServiceImpl entityService;

	// ------- Retrieve All GambleMatchEntities by GambleId -------------

	@RequestMapping(value = { "/gamble={gambleId}" }, method = RequestMethod.GET)
	public ResponseEntity<List<GambleMatchEntity>> listAllGambleMatchesByGamble(
			@PathVariable("gambleId") long gambleId) {
		List<GambleMatchEntity> entitiesList = entityService.findByGambleId(gambleId);
		if (entitiesList.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<GambleMatchEntity>>(entitiesList, HttpStatus.OK);
	}

	// ------- Retrieve All User's GambleMatchEntities by GambleId -------------

	@RequestMapping(value = { "/gamble={gambleId}/user={login}" }, method = RequestMethod.GET)
	public ResponseEntity<List<GambleMatchEntity>> listAllUserGambleMatchesByGamble(
			@PathVariable("gambleId") long gambleId, @PathVariable("login") String login) {
		List<GambleMatchEntity> entitiesList = entityService.findByUserAndGambleId(gambleId, login);
		if (entitiesList == null || entitiesList.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<GambleMatchEntity>>(entitiesList, HttpStatus.OK);
	}

	// ------------ Retrieve Single GambleRuleEntity-------------------------------

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getGambleGambleMatch(@PathVariable("id") String id) {
		logger.info("Fetching GambleMatcheEntity with id {}", id);
		GambleMatchEntity entity = null;
		try {
			entity = entityService.findById(id);

			if (entity == null) {
				logger.error("GambleMatcheEntity with id {} not found.", id);
				return new ResponseEntity(new CustomErrorType("GambleMatche with id " + id + " not found"),
						HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<GambleMatchEntity>(entity, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Getting data for GambleMatche {} error.", id, e);
			return new ResponseEntity(
					new CustomErrorType("Getting data for GambleMatche " + id + " error. " + e.getMessage()),
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

	// --------------- Save all Gamble MatchEntities by list ------------------
	@RequestMapping(value = "/save/all/list", method = RequestMethod.POST)
	public ResponseEntity<?> saveAllGambleMatches(@RequestBody List<GambleMatchEntity> saveList,
			UriComponentsBuilder ucBuilder) {
		logger.info("Fetching & Saveing Gamble MatchEntities from list");
		if (saveList == null || saveList.isEmpty()) {
			return new ResponseEntity<GambleMatchEntity>(HttpStatus.NO_CONTENT);
		}

		entityService.saveAll(saveList);
		return new ResponseEntity<GambleMatchEntity>(HttpStatus.NO_CONTENT);
	}

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

	// --------------- Delete a GambleMatchEntity ---------------------------------
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		logger.info("Fetching & Deleting GambleMatchEntity with id {}", id);

		GambleMatchEntity user = entityService.findById(id);
		if (user == null) {
			logger.error("Unable to delete. GambleMatchEntity with id {} not found.", id);
			return new ResponseEntity(
					new CustomErrorType("Unable to delete. GambleMatch with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		entityService.deleteById(id);
		return new ResponseEntity<GambleMatchEntity>(HttpStatus.NO_CONTENT);
	}

	// --------------- Delete a Gamble MatchEntities by gamble id ------------------
	@RequestMapping(value = "/delete/gambleid={gambleId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteGambleMatches(@PathVariable("gambleId") long gambleId) {
		logger.info("Fetching & Deleting Gamble MatchEntities with gamble id {}", gambleId);

		/*
		 * CompetitionEntity user = entityService.findById(id); if (user == null) {
		 * logger.error("Unable to delete. CompetitionEntity with id {} not found.",
		 * id); return new ResponseEntity( new
		 * CustomErrorType("Unable to delete. Competition with id " + id +
		 * " not found."), HttpStatus.NOT_FOUND); }
		 */
		entityService.deleteByGambleId(gambleId);
		return new ResponseEntity<GambleMatchEntity>(HttpStatus.NO_CONTENT);
	}

	// ------ Delete All Gamble Matches by gambleId, userId ------

	@RequestMapping(value = { "/delete/all/gamble={gambleId}/user={userId}" }, method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAllGambleMatchesByGambleIdAndUserId(@PathVariable("gambleId") long gambleId,
			@PathVariable("userId") long userId) {
		logger.info("Delete All Gamble Matches by gambleId= {}, userId= {}", gambleId, userId);
		entityService.deleteAllGambleMatchesByGambleIdAndUserId(gambleId, userId);

		return new ResponseEntity<GambleMatchEntity>(HttpStatus.NO_CONTENT);
	}

	// -- Delete all Gamble MatchEntities by GambleId And CompetitionId And Stage --
	@RequestMapping(value = "/delete/all/gamble={gambleId}/competition={competitionId}/stage={stage}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAllGambleMatchesByGambleIdAndCompetitionIdAndStage(
			@PathVariable("gambleId") long gambleId, @PathVariable("competitionId") long competitionId,
			@PathVariable("stage") String stage) {
		logger.info("Fetching & Deleting Gamble MatchEntities with gambleId={}, competitonId={} stage={} ", gambleId,
				competitionId, stage);

		entityService.deleteAllGambleMatchesByGambleIdAndCompetitionIdAndStage(gambleId, competitionId, stage);
		return new ResponseEntity<GambleMatchEntity>(HttpStatus.NO_CONTENT);
	}

	// --------------- Delete all Gamble MatchEntities by list ------------------
	@RequestMapping(value = "/delete/all/list", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAllGambleMatches(@RequestBody List<GambleMatchEntity> deleteList,
			UriComponentsBuilder ucBuilder) {
		logger.info("Fetching & Deleting Gamble MatchEntities from list");
		if (deleteList == null || deleteList.isEmpty()) {
			return new ResponseEntity<GambleMatchEntity>(HttpStatus.NO_CONTENT);
		}

		entityService.deleteAll(deleteList);
		return new ResponseEntity<GambleMatchEntity>(HttpStatus.NO_CONTENT);
	}

}

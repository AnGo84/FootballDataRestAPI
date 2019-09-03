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
import ua.footballdata.model.entity.SeasonEntity;
import ua.footballdata.service.SeasonEntityServiceImpl;

@RestController
@RequestMapping("/seasons")
public class SeasonController {

	public static final Logger logger = LoggerFactory.getLogger(SeasonController.class);

	@Autowired
	private SeasonEntityServiceImpl seasonEntityService;

	// ----------- Retrieve All SeasonEntities-------------------------------

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public ResponseEntity<List<SeasonEntity>> listAllSeasons() {
		List<SeasonEntity> seasons = seasonEntityService.findAll();
		if (seasons.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<SeasonEntity>>(seasons, HttpStatus.OK);
	}

	// ------------ Retrieve Single SeasonEntity-------------------------------

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getSeasonById(@PathVariable("id") long id) {
		logger.info("Fetching SeasonEntity with id {}", id);
		SeasonEntity seasonEntity = null;
		try {
			seasonEntity = seasonEntityService.findById(id);

			if (seasonEntity == null) {
				logger.error("SeasonEntity with id {} not found.", id);
				return new ResponseEntity(new CustomErrorType("SeasonEntity with id " + id + " not found"),
						HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<SeasonEntity>(seasonEntity, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Getting data for season id={} error.", id, e);
			return new ResponseEntity(
					new CustomErrorType("Getting data for season " + id + " error. " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	// -------------------Create a SeasonEntity ------------------------------

	/*
	 * @RequestMapping(value = "/add", method = RequestMethod.POST) public
	 * ResponseEntity<?> createUser(@RequestBody CompetitionEntity
	 * competitionEntity, UriComponentsBuilder ucBuilder) {
	 * logger.info("Creating CompetitionEntity : {}", competitionEntity);
	 * 
	 * if (competitionEntityService.isExist(competitionEntity)) {
	 * logger.error("Unable to create. A Competition with Id {} already exist",
	 * competitionEntity.getId()); return new ResponseEntity( new CustomErrorType(
	 * "Unable to create. A Competition with id " + competitionEntity.getId() +
	 * " already exist."), HttpStatus.CONFLICT); }
	 * competitionEntityService.save(competitionEntity);
	 * 
	 * HttpHeaders headers = new HttpHeaders();
	 * headers.setLocation(ucBuilder.path("/competitions/{id}").buildAndExpand(
	 * competitionEntity.getId()).toUri()); return new
	 * ResponseEntity<String>(headers, HttpStatus.CREATED); }
	 */

	// ------------------- Update a SeasonEntity ------------------------

	/*
	 * @RequestMapping(value = "/update", method = RequestMethod.PUT) public
	 * ResponseEntity<?> update(@RequestBody CompetitionEntity competitionEntity) {
	 * logger.info("Updating CompetitionEntity with id {}",
	 * competitionEntity.getId());
	 * 
	 * CompetitionEntity currentCompetitionEntity =
	 * competitionEntityService.findById(competitionEntity.getId());
	 * 
	 * if (currentCompetitionEntity == null) {
	 * logger.error("Unable to update. CompetitionEntity with id {} not found.",
	 * competitionEntity.getId()); return new ResponseEntity( new CustomErrorType(
	 * "Unable to upate. Competition with id " + competitionEntity.getId() +
	 * " not found."), HttpStatus.NOT_FOUND); }
	 * 
	 * competitionEntityService.update(currentCompetitionEntity); return new
	 * ResponseEntity<CompetitionEntity>(currentCompetitionEntity, HttpStatus.OK); }
	 */

	// --------------- Delete a CompetitionEntity ---------------------------------

	/*
	 * @RequestMapping(value = "/delete-{id}", method = RequestMethod.DELETE) public
	 * ResponseEntity<?> delete(@PathVariable("id") long id) {
	 * logger.info("Fetching & Deleting CompetitionEntity with id {}", id);
	 * 
	 * CompetitionEntity entity = competitionEntityService.findById(id); if (entity
	 * == null) {
	 * logger.error("Unable to delete. CompetitionEntity with id {} not found.",
	 * id); return new ResponseEntity( new
	 * CustomErrorType("Unable to delete. Competition with id " + id +
	 * " not found."), HttpStatus.NOT_FOUND); }
	 * competitionEntityService.deleteById(id); return new
	 * ResponseEntity<AppResponse>(new AppResponse("Deleted Competition: " +
	 * entity.getName()), HttpStatus.NO_CONTENT);
	 * 
	 * }
	 */

}

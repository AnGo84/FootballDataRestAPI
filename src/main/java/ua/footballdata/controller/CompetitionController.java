package ua.footballdata.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ua.footballdata.error.CustomErrorType;
import ua.footballdata.model.Competition;
import ua.footballdata.model.entity.CompetitionEntity;
import ua.footballdata.model.mapper.CompetitionMapper;
import ua.footballdata.service.CompetitionEntityServiceImpl;
import ua.footballdata.service.UpdateFromAPIService;
import ua.footballdata.serviceAPI.CompetitionAppServiceImp;

@RestController
@RequestMapping("/competitions")
public class CompetitionController {

	public static final Logger logger = LoggerFactory.getLogger(CompetitionController.class);

	/*@Value("${footballdata.token}")
	private String token;*/

	@Autowired
	private CompetitionEntityServiceImpl competitionEntityService;
	@Autowired
	private CompetitionAppServiceImp competitionAPIService;
	@Autowired
	private UpdateFromAPIService updateFromAPIService;

	//
	@Autowired
	private CompetitionMapper mapper;

	// ----------- Retrieve All CompetitionEntities-------------------------------

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public ResponseEntity<List<CompetitionEntity>> listAllCompetitions() {
		List<CompetitionEntity> competitions = competitionEntityService.findAll();
		if (competitions.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<CompetitionEntity>>(competitions, HttpStatus.OK);
	}

	// ------------ Retrieve Single CompetitionEntity-------------------------------

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCompetition(@PathVariable("id") long id) {
		logger.info("Fetching CompetitionEntity with id {}", id);
		CompetitionEntity competitionEntity = null;
		try {
			competitionEntity = competitionEntityService.findById(id);

			if (competitionEntity == null) {
				logger.error("CompetitionEntity with id {} not found.", id);
				return new ResponseEntity(new CustomErrorType("CompetitionEntity with id " + id + " not found"),
						HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<CompetitionEntity>(competitionEntity, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Getting data for competition {} error.", id, e);
			return new ResponseEntity(new CustomErrorType("Getting data for competition " + id + " error. " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	// -------------------Create a CompetitionEntity ------------------------------

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody CompetitionEntity competitionEntity,
			UriComponentsBuilder ucBuilder) {
		logger.info("Creating CompetitionEntity : {}", competitionEntity);

		if (competitionEntityService.isExist(competitionEntity)) {
			logger.error("Unable to create. A Competition with Id {} already exist", competitionEntity.getId());
			return new ResponseEntity(
					new CustomErrorType(
							"Unable to create. A Competition with id " + competitionEntity.getId() + " already exist."),
					HttpStatus.CONFLICT);
		}
		competitionEntityService.save(competitionEntity);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/competitions/{id}").buildAndExpand(competitionEntity.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a CompetitionEntity ------------------------

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody CompetitionEntity competitionEntity) {
		logger.info("Updating CompetitionEntity with id {}", competitionEntity.getId());

		CompetitionEntity currentCompetitionEntity = competitionEntityService.findById(competitionEntity.getId());

		if (currentCompetitionEntity == null) {
			logger.error("Unable to update. CompetitionEntity with id {} not found.", competitionEntity.getId());
			return new ResponseEntity(
					new CustomErrorType(
							"Unable to upate. Competition with id " + competitionEntity.getId() + " not found."),
					HttpStatus.NOT_FOUND);
		}

		competitionEntityService.update(currentCompetitionEntity);
		return new ResponseEntity<CompetitionEntity>(currentCompetitionEntity, HttpStatus.OK);
	}

	// --------------- Delete a CompetitionEntity ---------------------------------

	@RequestMapping(value = "/delete-{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting CompetitionEntity with id {}", id);

		CompetitionEntity user = competitionEntityService.findById(id);
		if (user == null) {
			logger.error("Unable to delete. CompetitionEntity with id {} not found.", id);
			return new ResponseEntity(
					new CustomErrorType("Unable to delete. Competition with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		competitionEntityService.deleteById(id);
		return new ResponseEntity<CompetitionEntity>(HttpStatus.NO_CONTENT);
	}

	// ------------ Update a CompetitionEntity from API --------------------

	@RequestMapping(value = "/updatefromapi/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCompetitionFromAPI(@PathVariable("id") long id) {
		logger.info("Fetching CompetitionEntity with id {} from API", id);

		Competition competition = competitionAPIService.findById(id);
		logger.info("Fetching Competition with id {} from API: {}", id, competition);

		if (competition == null) {
			logger.error("Competition with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Competition with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}

		CompetitionEntity competitionEntity = mapper.convertToEntity(competition);
		logger.info("Fetching CompetitionEntity: ", competitionEntity);

		/*
		 * CompetitionEntity competitionEntity = competitionService.findById(id); if
		 * (competitionEntity == null) {
		 * logger.error("CompetitionEntity with id {} not found.", id); return new
		 * ResponseEntity(new CustomErrorType("CompetitionEntity with id " + id +
		 * " not found"), HttpStatus.NOT_FOUND); }
		 */

		try {
			updateFromAPIService.updateCompetitionWithMatches(id);
		} catch (CustomErrorType e) {
			logger.error("Update Competition with id {} from API error {}.", id, e.getMessage());
			return new ResponseEntity(
					new CustomErrorType("Update Competition with id " + id + " from API error " + e.getMessage()),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return new ResponseEntity<CompetitionEntity>(competitionEntity, HttpStatus.OK);
	}

}

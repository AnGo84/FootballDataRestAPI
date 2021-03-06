package ua.footballdata.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import ua.footballdata.model.entity.GambleEntity;
import ua.footballdata.model.entity.GambleUserScore;
import ua.footballdata.service.GambleEntityServiceImpl;

@RestController
@RequestMapping("/gambles")
public class GambleController {

	public static final Logger logger = LoggerFactory.getLogger(GambleController.class);

	@Autowired
	private GambleEntityServiceImpl gambleEntityService;

	// ----------- Retrieve All GambleEntities-------------------------------

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public ResponseEntity<List<GambleEntity>> listAllGambles() {
		logger.info("Get All GambleEntities list");
		List<GambleEntity> gambles = gambleEntityService.findAll();
		if (gambles.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<GambleEntity>>(gambles, HttpStatus.OK);
	}

	// ----------- Retrieve All Active GambleEntities-------------------------------

	@RequestMapping(value = { "/active", "/active/" }, method = RequestMethod.GET)
	public ResponseEntity<List<GambleEntity>> listAllActiveGambles() {
		logger.info("Get All Active GambleEntities list");
		List<GambleEntity> gambles = gambleEntityService.findAllByActive(true);
		if (gambles.isEmpty()) {
			logger.info("Active GambleEntities list is null or empty");
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<GambleEntity>>(gambles, HttpStatus.OK);
	}

	// ----------- Retrieve All Active GambleEntities-------------------------------

	@RequestMapping(value = { "/active/user/{login}" }, method = RequestMethod.GET)
	public ResponseEntity<List<GambleEntity>> listAllActiveGamblesForUser(@PathVariable("login") String userLogin) {
		logger.info("Get All Active GambleEntities for user: {}", userLogin);
		List<GambleEntity> gambles = gambleEntityService.findAllActiveForUser(userLogin);
		if (gambles.isEmpty()) {
			logger.info("Active GambleEntities for user {} is null or empty", userLogin);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<GambleEntity>>(gambles, HttpStatus.OK);
	}

	// ------------ Retrieve Single GambleEntity-------------------------------

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getGamble(@PathVariable("id") long id) {
		logger.info("Fetching GambleEntity with id {}", id);
		GambleEntity GambleEntity = null;
		try {
			GambleEntity = gambleEntityService.findById(id);

			if (GambleEntity == null) {
				logger.error("GambleEntity with id {} not found.", id);
				return new ResponseEntity(new CustomErrorType("GambleEntity with id " + id + " not found"),
						HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<GambleEntity>(GambleEntity, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Getting data for Gamble id={} error.", id, e);
			return new ResponseEntity(
					new CustomErrorType("Getting data for Gamble " + id + " error. " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	// -------------------Create a GambleEntity ------------------------------

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody GambleEntity gambleEntity, UriComponentsBuilder ucBuilder) {
		logger.info("Creating GambleEntity : {}", gambleEntity);

		if (gambleEntityService.isExist(gambleEntity)) {
			logger.error("Unable to create. A Gamble with Id {} already exist", gambleEntity.getId());
			return new ResponseEntity(
					new CustomErrorType(
							"Unable to create. A Gamble with id " + gambleEntity.getId() + " already exist."),
					HttpStatus.CONFLICT);
		}
		gambleEntityService.save(gambleEntity);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/gambles/{id}").buildAndExpand(gambleEntity.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a GambleEntity ------------------------

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody GambleEntity gambleEntity) {
		logger.info("Updating GambleEntity with id {}", gambleEntity.getId());

		GambleEntity currentGambleEntity = gambleEntityService.findById(gambleEntity.getId());

		if (currentGambleEntity == null) {
			logger.error("Unable to update GambleEntity with id {} not found.", gambleEntity.getId());
			return new ResponseEntity(
					new CustomErrorType("Unable to update Gamble with id " + gambleEntity.getId() + " not found."),
					HttpStatus.NOT_FOUND);
		}

		gambleEntityService.update(gambleEntity);
		return new ResponseEntity<GambleEntity>(gambleEntity, HttpStatus.OK);
	}

	// --------------- Delete a GambleEntity ---------------------------------

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting GambleEntity with id {}", id);

		GambleEntity entity = gambleEntityService.findById(id);
		if (entity == null) {
			logger.error("Unable to delete. GambleEntity with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Gamble with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		gambleEntityService.deleteById(id);
		return new ResponseEntity<AppResponse>(new AppResponse("Deleted Gamble: " + entity.getName()),
				HttpStatus.NO_CONTENT);

	}

	// ----------- Retrieve Gamble USers' Scores ------------------------------

	@RequestMapping(value = { "{gambleId}/users/scores" }, method = RequestMethod.GET)
	public ResponseEntity<List<GambleUserScore>> getGambleUsersScores(@PathVariable("gambleId") long gambleId) {
		logger.info("Get Gamble Users' Scores for id: {}", gambleId);
		List<GambleUserScore> gambles = gambleEntityService.getGambleUsersScores(gambleId);
		if (gambles.isEmpty()) {
			logger.info("Users' Scores for gamble {} is null or empty", gambleId);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<GambleUserScore>>(gambles, HttpStatus.OK);
	}

}

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
import ua.footballdata.model.entity.User;
import ua.footballdata.service.UserService;

@RestController
@RequestMapping("/users")
public class UsersController {

	public static final Logger logger = LoggerFactory.getLogger(UsersController.class);

	@Autowired
	UserService userService; // Service which will do all data retrieval/manipulation work

	// -------------------Retrieve All Users ---------------------------------

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	// -------------------Retrieve Single User---------------------------------

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUserById(@PathVariable("id") long id) {
		logger.info("Fetching User with id {}", id);
		User user = userService.findById(id);
		if (user == null) {
			logger.error("User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("User with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/{login}", method = RequestMethod.GET)
	public ResponseEntity<?> getUserByLogin(@PathVariable("login") String login) {
		logger.info("Fetching User with Login {}", login);
		User user = userService.findByLogin(login);
		if (user == null) {
			logger.error("User with login {} not found.", login);
			return new ResponseEntity(new CustomErrorType("User with login " + login + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// -------------------Create a User-------------------------------------------

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", user);

		if (userService.isUserExist(user)) {
			logger.error("Unable to create. A User with Login {} already exist", user.getLogin());
			return new ResponseEntity(
					new CustomErrorType("Unable to create. A User with Login " + user.getLogin() + " already exist."),
					HttpStatus.CONFLICT);
		}
		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a User ---------------------------------------

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@RequestBody User user) {
		logger.info("Updating User {}", user);

		User currentUser = userService.findById(user.getId());

		if (currentUser == null) {
			logger.error("Unable to update. User with id {} not found.", user.getId());
			return new ResponseEntity(
					new CustomErrorType("Unable to upate. User with id " + user.getId() + " not found."),
					HttpStatus.NOT_FOUND);
		}

		userService.updateUser(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	/*
	 * @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT) public
	 * ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User
	 * user) { logger.info("Updating User with id {}", id);
	 * 
	 * User currentUser = userService.findById(id);
	 * 
	 * if (currentUser == null) {
	 * logger.error("Unable to update. User with id {} not found.", id); return new
	 * ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id +
	 * " not found."), HttpStatus.NOT_FOUND); }
	 * 
	 * userService.updateUser(user); return new ResponseEntity<User>(user,
	 * HttpStatus.OK); }
	 */

	// ------------------- Delete a User-----------------------------------------

	@RequestMapping(value = "/delete-{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting User with id {}", id);

		User user = userService.findById(id);
		if (user == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Users-----------------------------

	/*
	 * @RequestMapping(value = "/user/", method = RequestMethod.DELETE) public
	 * ResponseEntity<User> deleteAllUsers() { logger.info("Deleting All Users");
	 * 
	 * userService.deleteAllUsers(); return new
	 * ResponseEntity<User>(HttpStatus.NO_CONTENT); }
	 */

}

package ua.footballdata.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.footballdata.error.CustomErrorType;
import ua.footballdata.model.entity.User;
import ua.footballdata.model.entity.UserRole;
import ua.footballdata.service.UserRoleServiceImpl;
import ua.footballdata.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/userroles")
public class UserRoleController {

	public static final Logger logger = LoggerFactory.getLogger(UserRoleController.class);

	@Autowired
	UserRoleServiceImpl userRoleService;

	// -------------------Retrieve All User Roles ---------------------------------

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public ResponseEntity<List<UserRole>> listAllUsers() {
		List<UserRole> userRoles = userRoleService.findAllUserRoles();
		if (userRoles.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<UserRole>>(userRoles, HttpStatus.OK);
	}



}

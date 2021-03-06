package ua.footballdata.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.footballdata.model.entity.User;
import ua.footballdata.repositories.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserRepository userRepository;

	public List<User> findAllUsers() {
		logger.info("Find all users");
		return (List<User>) userRepository.findAll();
	}

	public List<User> findAllUsersByActive(boolean active) {
		logger.info("Find all users by active: " + active);
		/*
		 * List<User> list = findAllUsers();
		 * 
		 * List<User> filteredList = list.stream().filter(s -> s.isActive() ==
		 * active).collect(Collectors.toList()); logger.info("Get filteredList: {}",
		 * filteredList); if (filteredList == null) {
		 * logger.info("Get filteredList null"); return new ArrayList<>(); } return
		 * filteredList;
		 */
		/*
		 * if (active) { return userRepository.findByActive("true"); } else { return
		 * userRepository.findByActive("false"); }
		 */
		// return userRepository.findByActive(active);
		return userRepository.findByActive(active);

	}

	public User findById(long id) {
		logger.info("Find User with ID: " + id);

		return userRepository.findById(id).orElse(null);
		// return userRepository.getOne(id);
	}

	public User findByLogin(String login) {
		logger.info("Find User with Login: " + login);
		User user = userRepository.findByLogin(login);
		if (user != null) {
			return user;
		} else {
			return null;
		}
		/*
		 * Optional<User> optionalUser = userRepository.findByLogin(login); if
		 * (optionalUser != null) { return optionalUser.get(); } else { return null; }
		 */
	}

	public void saveUser(User user) {
		logger.info("Save User: " + user);
		userRepository.save(user);
	}

	public void updateUser(User user) {
		logger.info("Update User: " + user);
		saveUser(user);
	}

	public void deleteUserById(long id) {
		logger.info("Delete User with ID: " + id);
		userRepository.deleteById(Long.valueOf(id));
	}

	public boolean isUserExist(User user) {
		logger.info("Is User exist: " + user);
		return findByLogin(user.getLogin()) != null;
	}

}

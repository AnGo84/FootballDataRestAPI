package ua.footballdata.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.footballdata.model.entity.User;
import ua.footballdata.repositories.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	public User findById(long id) {
		return userRepository.getOne(id);
	}

	public User findByLogin(String login) {
		Optional<User> optionalUser = userRepository.findByLogin(login);
		if (optionalUser != null) {
			return optionalUser.get();
		} else {
			return null;
		}
	}

	public void saveUser(User user) {
		userRepository.save(user);
	}

	public void updateUser(User user) {
		saveUser(user);
	}

	public void deleteUserById(long id) {
		userRepository.deleteById(id);
	}

	public boolean isUserExist(User user) {
		return findByLogin(user.getLogin()) != null;
	}

}

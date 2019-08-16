package ua.footballdata.service;

import java.util.List;

import ua.footballdata.model.entity.User;

public interface UserService {

	User findById(long id);

	User findByLogin(String login);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserById(long id);

	List<User> findAllUsers();

	// void deleteAllUsers();

	boolean isUserExist(User user);

}

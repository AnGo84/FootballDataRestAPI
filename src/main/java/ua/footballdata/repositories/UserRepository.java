package ua.footballdata.repositories;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import ua.footballdata.model.entity.User;

@EnableScan
public interface UserRepository extends CrudRepository<User, Long> {

	User findByLogin(String login);

	List<User> findUsersById(Long id);

	// Optional<User> findByLogin(String login);

	// List<User> findAll();

}

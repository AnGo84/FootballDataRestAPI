package ua.footballdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.footballdata.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	// Optional<User> findByLogin(String login);
	User findByLogin(String login);

	void deleteById(long id);

}

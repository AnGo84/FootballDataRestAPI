package ua.footballdata.repositories;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import ua.footballdata.model.entity.UserRole;

@EnableScan
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

	UserRole findByName(String name);

	List<UserRole> findAll();

}

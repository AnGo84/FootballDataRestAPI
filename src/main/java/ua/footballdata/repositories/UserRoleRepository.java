package ua.footballdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.footballdata.model.entity.UserRole;


@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

	UserRole findByName(String name);

}

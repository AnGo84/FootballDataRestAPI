package ua.footballdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.footballdata.model.entity.TeamEntity;

@Repository
public interface TeamEntityRepository extends JpaRepository<TeamEntity, Long> {
	TeamEntity findByName(String name);

}

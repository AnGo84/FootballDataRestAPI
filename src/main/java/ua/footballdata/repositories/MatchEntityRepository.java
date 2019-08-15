package ua.footballdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.footballdata.model.entity.MatchEntity;

@Repository
public interface MatchEntityRepository extends JpaRepository<MatchEntity, Long> {
	// SeasonEntity findByName(String name);

}

package ua.footballdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.footballdata.model.entity.SeasonEntity;

@Repository
public interface SeasonEntityRepository extends JpaRepository<SeasonEntity, Long> {
	// SeasonEntity findByName(String name);
}

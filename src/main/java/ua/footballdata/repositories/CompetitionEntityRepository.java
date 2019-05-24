package ua.footballdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.footballdata.model.entity.CompetitionEntity;

@Repository
public interface CompetitionEntityRepository extends JpaRepository<CompetitionEntity, Long> {
	CompetitionEntity findByName(String name);
}

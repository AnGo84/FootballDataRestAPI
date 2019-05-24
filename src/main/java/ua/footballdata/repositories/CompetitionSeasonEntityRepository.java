package ua.footballdata.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.footballdata.model.entity.CompetitionSeasonEntity;

@Repository
public interface CompetitionSeasonEntityRepository extends JpaRepository<CompetitionSeasonEntity, Long> {
	CompetitionSeasonEntity findByCompetitionIdAndSeasonId(long competitionId, long seasonId);

	CompetitionSeasonEntity deleteByCompetitionIdAndSeasonId(long competitionId, long seasonId);

	List<CompetitionSeasonEntity> findByCompetitionId(long competitionId);
}

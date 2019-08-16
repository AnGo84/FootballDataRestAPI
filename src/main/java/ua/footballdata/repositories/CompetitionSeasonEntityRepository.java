package ua.footballdata.repositories;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import ua.footballdata.model.entity.CompetitionSeasonEntity;

//@Repository
@EnableScan
public interface CompetitionSeasonEntityRepository extends CrudRepository<CompetitionSeasonEntity, Long> {
	CompetitionSeasonEntity findByCompetitionIdAndSeasonId(long competitionId, long seasonId);

	CompetitionSeasonEntity deleteByCompetitionIdAndSeasonId(long competitionId, long seasonId);

	List<CompetitionSeasonEntity> findByCompetitionId(long competitionId);

	List<CompetitionSeasonEntity> findAll();
}

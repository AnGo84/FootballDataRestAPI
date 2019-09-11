package ua.footballdata.repositories;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import ua.footballdata.model.entity.GambleMatchEntity;

@EnableScan
public interface GambleMatchEntityRepository extends CrudRepository<GambleMatchEntity, String> {

	GambleMatchEntity findByGambleIdAndCompetitionIdAndMatchIdAndUserId(Long gambleId, Long competitionId, Long matchId,
			Long userId);

	List<GambleMatchEntity> findAll();

	List<GambleMatchEntity> findByGambleId(Long gambleId);

	List<GambleMatchEntity> findByCompetitionId(Long competitionId);

	List<GambleMatchEntity> findByGambleIdAndCompetitionId(Long gambleId, Long competitionId);

	List<GambleMatchEntity> findByGambleIdAndCompetitionIdAndMatchId(Long gambleId, Long competitionId, Long matchId);

	List<GambleMatchEntity> findByGambleIdAndUserId(Long gambleId, Long userId);

	void deleteByMatchId(Long matchId);

	void deleteByGambleId(Long gambleId);

	void deleteByGambleIdAndUserId(Long gambleId, Long userId);

	void deleteByGambleIdAndCompetitionIdAndStage(Long gambleId, Long competitionId, String stage);
}

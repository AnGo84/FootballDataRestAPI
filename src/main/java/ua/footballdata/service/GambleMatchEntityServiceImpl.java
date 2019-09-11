package ua.footballdata.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.util.StringUtils;

import ua.footballdata.model.entity.GambleMatchEntity;
import ua.footballdata.model.entity.User;
import ua.footballdata.repositories.GambleMatchEntityRepository;

@Service("gambleMatchEntityService")
public class GambleMatchEntityServiceImpl {
	public static final Logger logger = LoggerFactory.getLogger(GambleMatchEntityServiceImpl.class);
	@Autowired
	private GambleMatchEntityRepository repository;
	@Autowired
	private UserServiceImpl userService;

	public GambleMatchEntity findById(String id) {
		// return repository.getOne(id);
		return repository.findById(id).orElse(null);
	}

	public List<GambleMatchEntity> findByGambleId(Long gambleId) {
		logger.info("find GambleMatchEntities By GambleId: " + gambleId);
		return repository.findByGambleId(gambleId);
	}

	public void save(GambleMatchEntity object) {
		logger.info("Save GambleMatchEntity: " + object);
		repository.save(object);

	}

	public void saveAll(List<GambleMatchEntity> objects) {
		logger.info("Save all GambleMatchEntities: " + objects.size());
		// TODO remove
		for (GambleMatchEntity gambleMatchEntity : objects) {
			logger.info("	GambleMatchEntity: " + gambleMatchEntity);
		}

		repository.saveAll(objects);

	}

	public void update(GambleMatchEntity object) {
		logger.info("Update GambleMatchEntity: " + object);
		this.save(object);
	}

	public void deleteById(String id) {
		logger.info("Delete GambleMatchEntities By Id: " + id);
		repository.deleteById(id);
	}

	public void deleteByMatchId(Long matchId) {
		logger.info("Delete GambleMatchEntities By MatchId: " + matchId);
		repository.deleteByMatchId(matchId);
	}

	public void deleteByGambleId(Long gambleId) {
		logger.info("Delete GambleMatchEntities By GambleId: " + gambleId);
		repository.deleteByGambleId(gambleId);
	}

	public void deleteAllGambleMatchesByGambleIdAndUserId(long gambleId, long userId) {
		logger.info("Delete All GambleMatchEntities By GambleId= {} And UserId= {}", gambleId, userId);
		repository.deleteByGambleIdAndUserId(gambleId, userId);
	}

	public void deleteAllGambleMatchesByGambleIdAndCompetitionIdAndStage(long gambleId, long competitionId,
			String stage) {
		logger.info("Delete All GambleMatchEntities By GambleId= {} And CompetitionId= {} And Stage {}", gambleId,
				competitionId, stage);
		repository.deleteByGambleIdAndCompetitionIdAndStage(gambleId, competitionId, stage);
	}

	public void deleteAll(List<GambleMatchEntity> deleteList) {
		logger.info("Delete All: " + deleteList.size());
		repository.deleteAll(deleteList);
	}

	public List<GambleMatchEntity> findAll() {
		logger.info("Find all");
		return repository.findAll();
	}

	public boolean isExist(GambleMatchEntity object) {
		logger.info("Is Exist GambleMatchEntity: {}", object);
		GambleMatchEntity findGambleMatchEntity = repository.findByGambleIdAndCompetitionIdAndMatchIdAndUserId(
				object.getGambleId(), object.getCompetitionId(), object.getMatchId(), object.getUserId());
		logger.info("GambleMatchEntity: " + findGambleMatchEntity);
		return findGambleMatchEntity != null;
	}

	public boolean isExist(String id) {
		logger.info("Is Exist GambleMatchEntity by id= {}", id);
		GambleMatchEntity entity = findById(id);
		logger.info("GambleMatchEntity: " + entity);
		return entity != null;
	}

	public List<GambleMatchEntity> findByUserAndGambleId(long gambleId, String login) {
		logger.info("find GambleMatchEntities By GambleId: {} and user: {}", gambleId, login);
		if (StringUtils.isNullOrEmpty(login)) {
			return null;
		}
		User user = userService.findByLogin(login);
		if (user == null) {
			return null;
		}
		return repository.findByGambleIdAndUserId(gambleId, user.getId());

	}

}

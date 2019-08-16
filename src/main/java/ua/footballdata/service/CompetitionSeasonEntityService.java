package ua.footballdata.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ua.footballdata.model.entity.CompetitionSeasonEntity;
import ua.footballdata.repositories.CompetitionSeasonEntityRepository;

@Service("competitionSeasonEntityService")
public class CompetitionSeasonEntityService {
	// @Autowired
	private CompetitionSeasonEntityRepository repository;

	public List<CompetitionSeasonEntity> findByCompetitionId(long id) {
		return repository.findByCompetitionId(id);
	}

	public void save(CompetitionSeasonEntity object) {
		repository.save(object);

	}

	public void update(CompetitionSeasonEntity object) {
		this.save(object);
	}

	public List<CompetitionSeasonEntity> findAll() {
		return repository.findAll();
	}

	public boolean isExist(CompetitionSeasonEntity object) {
		return repository.findByCompetitionIdAndSeasonId(object.getCompetitionId(), object.getSeasonId()) != null;
	}

}

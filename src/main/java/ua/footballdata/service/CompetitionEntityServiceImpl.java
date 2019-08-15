package ua.footballdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.footballdata.model.entity.CompetitionEntity;
import ua.footballdata.repositories.CompetitionEntityRepository;

@Service("competitionEntityService")
public class CompetitionEntityServiceImpl implements CommonService<CompetitionEntity> {
	@Autowired
	private CompetitionEntityRepository repository;

	@Override
	public CompetitionEntity findById(long id) {
		// return repository.getOne(id);
		// return repository.findById(id).orElse(null);
		return repository.findById(id);
	}

	@Override
	public CompetitionEntity findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public void save(CompetitionEntity object) {
		repository.save(object);

	}

	@Override
	public void update(CompetitionEntity object) {
		this.save(object);
	}

	@Override
	public void deleteById(long id) {
		repository.deleteById(id);
	}

	@Override
	public List<CompetitionEntity> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean isExist(CompetitionEntity object) {
		return findByName(object.getName()) != null;
	}

}

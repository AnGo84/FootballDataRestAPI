package ua.footballdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.footballdata.model.entity.TeamEntity;
import ua.footballdata.repositories.TeamEntityRepository;

@Service("teamEntityService")
public class TeamEntityServiceImpl implements CommonService<TeamEntity> {
	@Autowired
	private TeamEntityRepository repository;

	@Override
	public TeamEntity findById(long id) {
		// return repository.getOne(id);
		return repository.findById(id).orElse(null);
	}

	@Override
	public TeamEntity findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public void save(TeamEntity object) {
		repository.save(object);

	}

	@Override
	public void update(TeamEntity object) {
		this.save(object);
	}

	@Override
	public void deleteById(long id) {
		repository.deleteById(id);
	}

	@Override
	public List<TeamEntity> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean isExist(TeamEntity object) {
		return findByName(object.getName()) != null;
	}

}

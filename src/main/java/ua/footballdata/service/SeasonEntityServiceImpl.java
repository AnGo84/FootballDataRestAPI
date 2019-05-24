package ua.footballdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.footballdata.model.entity.SeasonEntity;
import ua.footballdata.repositories.SeasonEntityRepository;

@Service("seasonEntityService")
public class SeasonEntityServiceImpl implements CommonService<SeasonEntity> {
	@Autowired
	private SeasonEntityRepository repository;

	@Override
	public SeasonEntity findById(long id) {
		// return repository.getOne(id);
		return repository.findById(id).orElse(null);
	}

	@Override
	public SeasonEntity findByName(String name) {
		// return repository.findByName(name);
		return null;
	}

	@Override
	public void save(SeasonEntity object) {
		repository.save(object);

	}

	@Override
	public void update(SeasonEntity object) {
		this.save(object);
	}

	@Override
	public void deleteById(long id) {
		repository.deleteById(id);
	}

	@Override
	public List<SeasonEntity> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean isExist(SeasonEntity object) {
		return findById(object.getId()) != null;
	}

}

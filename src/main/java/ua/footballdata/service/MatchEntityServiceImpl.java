package ua.footballdata.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.footballdata.model.entity.MatchEntity;
import ua.footballdata.repositories.MatchEntityRepository;

@Service("matchEntityService")
public class MatchEntityServiceImpl implements CommonService<MatchEntity> {
	public static final Logger logger = LoggerFactory.getLogger(MatchEntityServiceImpl.class);
	@Autowired
	private MatchEntityRepository repository;

	@Override
	public MatchEntity findById(long id) {
		// return repository.getOne(id);
		return repository.findById(id).orElse(null);
	}

	@Override
	public MatchEntity findByName(String name) {
		// return repository.findByName(name);
		return null;
	}

	@Override
	public void save(MatchEntity object) {
		repository.save(object);

	}

	@Override
	public void update(MatchEntity object) {
		this.save(object);
	}

	@Override
	public void deleteById(long id) {
		repository.deleteById(id);
	}

	@Override
	public List<MatchEntity> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean isExist(MatchEntity object) {
		return findById(object.getId()) != null;
	}

	public boolean isExist(long id) {
		MatchEntity entity = findById(id);
		logger.info("MatchEntity: " + entity);
		return entity != null;
	}

}

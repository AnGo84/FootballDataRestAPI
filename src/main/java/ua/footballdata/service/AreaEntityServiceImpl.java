package ua.footballdata.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.footballdata.model.entity.AreaEntity;
import ua.footballdata.repositories.AreaEntityRepository;

@Service("areaEntityService")
@Transactional
public class AreaEntityServiceImpl implements CommonService<AreaEntity> {
	public static final Logger logger = LoggerFactory.getLogger(AreaEntityServiceImpl.class);
	@Autowired
	private AreaEntityRepository repository;

	@Override
	public AreaEntity findById(long id) {
		// return repository.getOne(id);
		// return repository.findById(id).orElse(null);

		return repository.findById(id);
	}

	@Override
	public AreaEntity findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public void save(AreaEntity object) {
		repository.save(object);

	}

	@Override
	public void update(AreaEntity object) {
		this.save(object);
	}

	@Override
	public void deleteById(long id) {
		repository.deleteById(id);
	}

	@Override
	public List<AreaEntity> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean isExist(AreaEntity object) {
		return findByName(object.getName()) != null;
	}

	public boolean isExist(long id) {
		AreaEntity areaEntity = findById(id);
		logger.info("AreaEntity: " + areaEntity);
		return areaEntity != null;
	}

}

package ua.footballdata.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.footballdata.model.entity.GambleEntity;
import ua.footballdata.repositories.GambleEntityRepository;

@Service("gambleEntityService")
public class GambleEntityServiceImpl implements CommonService<GambleEntity> {
	public static final Logger logger = LoggerFactory.getLogger(GambleEntityServiceImpl.class);
	@Autowired
	private GambleEntityRepository repository;

	@Override
	public GambleEntity findById(long id) {
		// return repository.getOne(id);
		return repository.findById(id).orElse(null);
	}

	@Override
	public GambleEntity findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public void save(GambleEntity object) {
		repository.save(object);

	}

	@Override
	public void update(GambleEntity object) {
		this.save(object);
	}

	@Override
	public void deleteById(long id) {
		repository.deleteById(id);
	}

	@Override
	public List<GambleEntity> findAll() {
		return repository.findAll();
	}

	public List<GambleEntity> findAllByActive(boolean active) {
		logger.info("Active: {}", active);
		List<GambleEntity> list = repository.findByActive(active);
		logger.info("Active list: {}", list);
		/*
		 * List<GambleRuleEntity> filteredList = list.stream().filter(s -> s.isActive()
		 * == active) .collect(Collectors.toList()); logger.info("Get filteredList: {}",
		 * filteredList); if (filteredList == null) {
		 * logger.info("Get filteredList null"); return new ArrayList<>(); } return
		 * filteredList;
		 */
		return list;

	}

	@Override
	public boolean isExist(GambleEntity object) {
		return findById(object.getId()) != null;
	}

	public boolean isExist(long id) {
		GambleEntity entity = findById(id);
		logger.info("GambleEntity: " + entity);
		return entity != null;
	}

}

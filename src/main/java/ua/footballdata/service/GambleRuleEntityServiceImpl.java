package ua.footballdata.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.footballdata.model.entity.GambleRuleEntity;
import ua.footballdata.repositories.GambleRuleEntityRepository;

@Service("gambleRuleEntityService")
public class GambleRuleEntityServiceImpl implements CommonService<GambleRuleEntity> {
	public static final Logger logger = LoggerFactory.getLogger(GambleRuleEntityServiceImpl.class);
	@Autowired
	private GambleRuleEntityRepository repository;

	@Override
	public GambleRuleEntity findById(long id) {
		// return repository.getOne(id);
		return repository.findById(id).orElse(null);
	}

	@Override
	public GambleRuleEntity findByName(String name) {
		return repository.findByFullName(name);
	}

	@Override
	public void save(GambleRuleEntity object) {
		repository.save(object);

	}

	@Override
	public void update(GambleRuleEntity object) {
		this.save(object);
	}

	@Override
	public void deleteById(long id) {
		repository.deleteById(id);
	}

	@Override
	public List<GambleRuleEntity> findAll() {
		return repository.findAll();
	}

	public List<GambleRuleEntity> findByActive(boolean active) {
		logger.info("Active: {}", active);
		return repository.findByActive(active);
		/*
		 * List<GambleRuleEntity> list = repository.findAll();
		 * 
		 * List<GambleRuleEntity> filteredList = list.stream().filter(s -> s.isActive()
		 * == active) .collect(Collectors.toList()); logger.info("Get filteredList: {}",
		 * filteredList); if (filteredList == null) {
		 * logger.info("Get filteredList null"); return new ArrayList<>(); } return
		 * filteredList;
		 */
		/*
		 * } catch (Exception e) { logger.error("Error: " + e.getMessage(), e); return
		 * null; }
		 */

	}

	@Override
	public boolean isExist(GambleRuleEntity object) {
		return findByName(object.getFullName()) != null;
	}

	public boolean isExist(long id) {
		GambleRuleEntity entity = findById(id);
		logger.info("GambleRuleEntity: " + entity);
		return entity != null;
	}

}

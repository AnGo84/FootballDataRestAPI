package ua.footballdata.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.footballdata.model.entity.GambleEntity;
import ua.footballdata.model.entity.GambleUser;
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
		logger.info("Save GambleEntity: {}", object);
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
		return list;
	}

	public List<GambleEntity> findAllActiveForUser(String login) {
		logger.info("User Login: {}", login);
		if (login == null || login.isEmpty()) {
			return new ArrayList<>();
		}
		List<GambleEntity> list = repository.findByActive(true);
		List<GambleEntity> userList = new ArrayList<>();
		for (GambleEntity gambleEntity : list) {
			if (gambleEntity.getParticipants() != null) {
				for (GambleUser gambleUser : gambleEntity.getParticipants()) {
					if (gambleUser.getLogin().equals(login)) {
						userList.add(gambleEntity);
						continue;
					}

				}
			}
		}

		// List<GambleEntity> userList = list.stream().filter(c -> c.getParticipants() >
		// 100).collect(Collectors.toList());
		return userList;
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

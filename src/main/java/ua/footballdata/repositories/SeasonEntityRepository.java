package ua.footballdata.repositories;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import ua.footballdata.model.entity.SeasonEntity;

//@Repository
@EnableScan
public interface SeasonEntityRepository extends CrudRepository<SeasonEntity, Long> {
	// SeasonEntity findByName(String name);
	List<SeasonEntity> findAll();
}

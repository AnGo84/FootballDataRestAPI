package ua.footballdata.repositories;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import ua.footballdata.model.entity.MatchEntity;

//@Repository
@EnableScan
public interface MatchEntityRepository extends CrudRepository<MatchEntity, Long> {
	// SeasonEntity findByName(String name);
	List<MatchEntity> findAll();
}

package ua.footballdata.repositories;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import ua.footballdata.model.entity.CompetitionEntity;

//@Repository
@EnableScan
public interface CompetitionEntityRepository extends CrudRepository<CompetitionEntity, Long> {
	CompetitionEntity findByName(String name);

	List<CompetitionEntity> findAll();
}

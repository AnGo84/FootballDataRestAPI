package ua.footballdata.repositories;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import ua.footballdata.model.entity.TeamEntity;

//@Repository
@EnableScan
public interface TeamEntityRepository extends CrudRepository<TeamEntity, Long> {
	TeamEntity findByName(String name);

	List<TeamEntity> findAll();
}

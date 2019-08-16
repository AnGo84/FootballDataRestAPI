package ua.footballdata.repositories;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import ua.footballdata.model.entity.AreaEntity;

//@Repository
@EnableScan
public interface AreaEntityRepository extends CrudRepository<AreaEntity, Long> {
	AreaEntity findByName(String name);

	List<AreaEntity> findAll();
}

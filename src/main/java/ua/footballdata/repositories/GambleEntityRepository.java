package ua.footballdata.repositories;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import ua.footballdata.model.entity.GambleEntity;

@EnableScan
public interface GambleEntityRepository extends CrudRepository<GambleEntity, Long> {

	GambleEntity findByName(String name);

	List<GambleEntity> findAll();

	List<GambleEntity> findByActive(Boolean active);

}

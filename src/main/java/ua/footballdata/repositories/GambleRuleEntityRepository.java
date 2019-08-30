package ua.footballdata.repositories;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import ua.footballdata.model.entity.GambleRuleEntity;

@EnableScan
public interface GambleRuleEntityRepository extends CrudRepository<GambleRuleEntity, Long> {
	GambleRuleEntity findByFullName(String name);

	List<GambleRuleEntity> findAll();

	List<GambleRuleEntity> findByActive(Boolean active);
}

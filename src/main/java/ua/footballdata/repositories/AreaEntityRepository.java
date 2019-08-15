package ua.footballdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.footballdata.model.entity.AreaEntity;

@Repository
//@Profile("mysql")
public interface AreaEntityRepository extends JpaRepository<AreaEntity, Long> {
	AreaEntity findByName(String name);

	AreaEntity findById(long id);

	void deleteById(long id);

}

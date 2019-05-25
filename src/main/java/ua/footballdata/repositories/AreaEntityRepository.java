package ua.footballdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.footballdata.model.entity.AreaEntity;

@Repository
public interface AreaEntityRepository extends JpaRepository<AreaEntity, Long> {
	AreaEntity findByName(String name);
}

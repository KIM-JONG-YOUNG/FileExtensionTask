package edu.jong.server.repository;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import edu.jong.server.entity.CustomExtensionEntity;

public interface CustomExtensionRepository extends JpaRepository<CustomExtensionEntity, Long> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select c from CustomExtensionEntity c where c.no = :no")
	Optional<CustomExtensionEntity> findByIdForUpdate(Long no);

	boolean existsByExtension(String extension);
}

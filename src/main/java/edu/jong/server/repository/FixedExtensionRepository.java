package edu.jong.server.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.jong.server.entity.FixedExtensionEntity;
import edu.jong.server.enums.FixedExtensionState;

public interface FixedExtensionRepository extends JpaRepository<FixedExtensionEntity, Long> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select f from FixedExtensionEntity f where f.no = :no")
	Optional<FixedExtensionEntity> findByIdForUpdate(@Param("no") Long no);

	List<FixedExtensionEntity> findByState(FixedExtensionState state);

	boolean existsByExtension(String extension);

}

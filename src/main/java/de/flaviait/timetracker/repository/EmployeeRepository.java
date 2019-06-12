package de.flaviait.timetracker.repository;

import de.flaviait.timetracker.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    Optional<EmployeeEntity> findByObjectId(String objectId);

}

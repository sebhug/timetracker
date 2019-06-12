package de.flaviait.timetracker.repository;

import de.flaviait.timetracker.entity.TimeRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TimeRecordRepository extends JpaRepository<TimeRecordEntity, Long> {
    Optional<TimeRecordEntity> findByObjectId(String objectId);
}

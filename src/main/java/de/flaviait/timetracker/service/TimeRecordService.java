package de.flaviait.timetracker.service;

import de.flaviait.timetracker.entity.TimeRecordEntity;
import de.flaviait.timetracker.exception.EmployeeNotFoundException;
import de.flaviait.timetracker.exception.TimeRecordNotFoundException;
import de.flaviait.timetracker.repository.TimeRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TimeRecordService {

    private final TimeRecordRepository timeRecordRepository;
    private final EmployeeService employeeService;

    @Transactional
    public TimeRecordEntity createTimeRecord(TimeRecordEntity timeRecordEntity) {
        timeRecordEntity.setObjectId(UUID.randomUUID().toString());
        timeRecordEntity.setEmployee(
                employeeService.getEmployee(timeRecordEntity
                        .getEmployee()
                        .getObjectId())
                        .orElseThrow(() -> new EmployeeNotFoundException(timeRecordEntity.getEmployee().getObjectId()))
        );
        return timeRecordRepository.save(timeRecordEntity);
    }

    @Transactional
    public Optional<TimeRecordEntity> getTimeRecord(String objectId) {
        return timeRecordRepository.findByObjectId(objectId);
    }

    @Transactional
    public TimeRecordEntity updateTimeRecord(TimeRecordEntity update) {
        Optional<TimeRecordEntity> timeRecord = getTimeRecord(update.getObjectId());
        if (timeRecord.isPresent()) {
            return updateEntity(timeRecord.get(), update);
        }
        return createTimeRecord(update);
    }

    private TimeRecordEntity updateEntity(TimeRecordEntity existing, TimeRecordEntity update) {
        existing.setDescription(update.getDescription());
        existing.setMinutes(update.getMinutes());
        existing.setRecordDate(update.getRecordDate());
        if (!existing.getEmployee().getObjectId().equals(update.getEmployee().getObjectId())) {
            existing.setEmployee(employeeService.getEmployee(update
                    .getEmployee()
                    .getObjectId())
                    .orElseThrow(() -> new EmployeeNotFoundException(update.getEmployee().getObjectId())));
        }
        return timeRecordRepository.save(existing);
    }

    @Transactional
    public void deleteTimeRecordByObjectId(String objectId) {
        timeRecordRepository.delete(getTimeRecord(objectId).orElseThrow(() -> new TimeRecordNotFoundException(objectId)));
    }
}

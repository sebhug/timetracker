package de.flaviait.timetracker.service;

import de.flaviait.timetracker.entity.EmployeeEntity;
import de.flaviait.timetracker.exception.EmployeeNotFoundException;
import de.flaviait.timetracker.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeEntity createNewEmployee(EmployeeEntity employeeEntity) {
        employeeEntity.setObjectId(UUID.randomUUID().toString());
        return employeeRepository.save(employeeEntity);
    }

    public Optional<EmployeeEntity> getEmployee(String objectId) {
        return employeeRepository.findByObjectId(objectId);
    }

    public void deleteEmployee(String objectId) {
        employeeRepository.delete(getEmployee(objectId).orElseThrow(() -> new EmployeeNotFoundException(objectId)));
    }
}

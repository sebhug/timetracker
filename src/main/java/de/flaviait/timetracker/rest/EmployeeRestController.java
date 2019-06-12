package de.flaviait.timetracker.rest;

import de.flaviait.timetracker.dto.EmployeeDto;
import de.flaviait.timetracker.entity.EmployeeEntity;
import de.flaviait.timetracker.mapper.EmployeeMapper;
import de.flaviait.timetracker.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeRestController implements BasicRestController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @PostMapping
    public ResponseEntity post(@RequestBody @Valid EmployeeDto request) {
        EmployeeEntity entity = employeeService.createNewEmployee(employeeMapper.dtoToEntity(request));
        return ResponseEntity.created(getLocationHeader(entity.getObjectId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> get(@PathVariable String id) {
        return ResponseEntity.of(employeeService.getEmployee(id)
                .map(employeeMapper::entityToDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

}

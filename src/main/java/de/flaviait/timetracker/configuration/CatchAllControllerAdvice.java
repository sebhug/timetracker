package de.flaviait.timetracker.configuration;

import de.flaviait.timetracker.dto.ErrorDto;
import de.flaviait.timetracker.exception.EmployeeNotFoundException;
import de.flaviait.timetracker.exception.TimeRecordNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CatchAllControllerAdvice {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        return ResponseEntity.badRequest()
                .body(
                        ErrorDto.builder()
                                .message("Employee with id: " + ex.getObjectId() + " not found")
                                .build()
                );
    }

    @ExceptionHandler(TimeRecordNotFoundException.class)
    public ResponseEntity handleTimeRecordNotFoundException(TimeRecordNotFoundException ex) {
        return ResponseEntity.badRequest()
                .body(
                        ErrorDto.builder()
                                .message("Time Record with id: " + ex.getObjectId() + " not found")
                                .build()
                );
    }
}

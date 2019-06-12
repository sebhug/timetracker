package de.flaviait.timetracker.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.Instant;

@Data
public class TimeRecordDto {
    @Null(message = "identifier must be null")
    private String id;
    @NotNull(message = "minutes are missing")
    private Integer minutes;
    @NotNull(message = "record date is missing")
    private Instant recordDate;
    @Length(max = 255, message = "max length exceeded")
    private String description;
    @NotNull(message = "missing employee")
    private String employeeId;
}

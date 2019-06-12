package de.flaviait.timetracker.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class EmployeeDto {
    @Null(message = "identifier must be null")
    private String id;
    @NotNull
    @Length(min = 1, max = 255)
    private String name;
}

package de.flaviait.timetracker.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorDto {
    private final String message;
}

package de.flaviait.timetracker.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TimeRecordNotFoundException extends RuntimeException {
    private final String objectId;
}

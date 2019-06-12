package de.flaviait.timetracker.fixture

import de.flaviait.timetracker.dto.TimeRecordDto
import de.flaviait.timetracker.entity.TimeRecordEntity
import net.bytebuddy.utility.RandomString

import java.time.Instant

class TimeRecordFixture {

    static TimeRecordDto validTimeRecordDto() {
        new TimeRecordDto(
                id: "STANDARD_TIME_RECORD",
                minutes: 10,
                recordDate: Instant.now(),
                description: new RandomString(36).nextString(),
                employeeId: EmployeeFixture.validEmployeeDto().id
        )
    }

    static TimeRecordEntity validAndPersistedTimeRecordEntity() {
        new TimeRecordEntity(
                id: 1L,
                objectId: "STANDARD_TIME_RECORD",
                minutes: 10,
                recordDate: Instant.now(),
                description: new RandomString(36).nextString(),
                employee: EmployeeFixture.validAndPersistedEmployeeEntity(),
                creationTimestamp: Instant.now(),
                updateTimestamp: Instant.now(),
                version: 1L
        )
    }

    static TimeRecordEntity validTimeRecordEntityReadyForCreation() {
        new TimeRecordEntity(
                minutes: 10,
                recordDate: Instant.now(),
                employee: EmployeeFixture.validAndPersistedEmployeeEntity()
        )
    }

}

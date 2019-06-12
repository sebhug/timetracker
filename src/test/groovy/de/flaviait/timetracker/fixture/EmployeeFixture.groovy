package de.flaviait.timetracker.fixture

import de.flaviait.timetracker.dto.EmployeeDto
import de.flaviait.timetracker.entity.EmployeeEntity
import net.bytebuddy.utility.RandomString

import java.time.Instant

class EmployeeFixture {

    static EmployeeDto validEmployeeDtoForCreation(name = new RandomString(36).nextString()) {
        new EmployeeDto(
                name: name
        )
    }

    static EmployeeDto validEmployeeDto(name = new RandomString(36).nextString()) {
        new EmployeeDto(
                id: "STANDARD_EMPLOYEE",
                name: name
        )
    }

    static EmployeeEntity validAndPersistedEmployeeEntity(name = new RandomString(36).nextString()) {
        new EmployeeEntity(
                id: 1L,
                objectId: "STANDARD_EMPLOYEE",
                name: name,
                creationTimestamp: Instant.now(),
                updateTimestamp: Instant.now(),
                version: 1L
        )
    }

    static EmployeeEntity validEmployeeEntityForCreation(name = new RandomString(36).nextString()) {
        new EmployeeEntity(
                name: name
        )
    }

}

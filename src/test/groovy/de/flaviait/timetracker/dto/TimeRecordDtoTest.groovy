package de.flaviait.timetracker.dto

import net.bytebuddy.utility.RandomString
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory
import java.time.Instant

class TimeRecordDtoTest extends Specification {

    @Shared
    Validator validator

    def setupSpec() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()
        validator = validatorFactory.validator
    }

    @Unroll
    def "#description -> #valid"() {

        expect: "#valid"
        def violations = validator.validate(dto)
        violations.isEmpty() == valid

        where: "#description"

        dto | description             | valid
        new TimeRecordDto(
                id: new RandomString(255).nextString(),
                minutes: 10,
                recordDate: Instant.now(),
                description: new RandomString(255).nextString(),
                employeeId: new RandomString(255).nextString()
        )   | "TimeRecordDto with id" | false
        new TimeRecordDto(
                minutes: 10,
                recordDate: Instant.now(),
                description: new RandomString(255).nextString(),
                employeeId: new RandomString(255).nextString()
        )   | "correct TimeRecordDto" | true
        new TimeRecordDto(
                recordDate: Instant.now(),
                description: new RandomString(255).nextString(),
                employeeId: new RandomString(255).nextString()
        )   | "missing minutes" | false
        new TimeRecordDto(
                minutes: 10,
                description: new RandomString(255).nextString(),
                employeeId: new RandomString(255).nextString()
        )   | "missing recordDate" | false
        new TimeRecordDto(
                minutes: 10,
                recordDate: Instant.now(),
                employeeId: new RandomString(255).nextString()
        )   | "missing Description" | true
        new TimeRecordDto(
                minutes: 10,
                recordDate: Instant.now(),
                description: new RandomString(256).nextString(),
                employeeId: new RandomString(255).nextString()
        )   | "description too long" | false
        new TimeRecordDto(
                minutes: 10,
                recordDate: Instant.now(),
                description: new RandomString(255).nextString(),
        )   | "missing employee" | false
    }
}

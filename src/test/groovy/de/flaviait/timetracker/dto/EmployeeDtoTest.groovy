package de.flaviait.timetracker.dto

import net.bytebuddy.utility.RandomString
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory

class EmployeeDtoTest extends Specification {

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

        dto               | description            | valid
        new EmployeeDto(
                id: "abc",
                name: "Peter Mustermann"
        )                 | "Employee with id"     | false
        new EmployeeDto(
                name: "Peter Mustermann"
        )                 | "correct Employee DTO" | true
        new EmployeeDto(
                name: new RandomString(256).nextString()
        )                 | "name too long"        | false
        new EmployeeDto() | "missing name"         | false
        new EmployeeDto(
                name: new RandomString(255).nextString()
        )                 | "exact 255 characters long name"  | true
    }
}

package de.flaviait.timetracker.mapper

import de.flaviait.timetracker.fixture.EmployeeFixture
import org.mapstruct.factory.Mappers
import spock.lang.Specification

class EmployeeMapperTest extends Specification {

    EmployeeMapper serviceUnderTest = Mappers.getMapper(EmployeeMapper.class)

    def "convert EmployeeDto to EmployeeEntity"() {

        given: "an EmployeeDto"
        def employeeDto = EmployeeFixture.validEmployeeDto()

        when: "convert EmployeeDto to EmployeeEntity"
        def resultEntity = serviceUnderTest.dtoToEntity(employeeDto)

        then: "no Exception thrown"
        noExceptionThrown()

        and: "resultEntity.name has been mapped correctly"
        resultEntity.name == employeeDto.name

        and: "resultEntity.objectId has been mapped correctly"
        resultEntity.objectId == employeeDto.id

        and: "all other fields of resultEntity are null"
        resultEntity.id == null
        resultEntity.creationTimestamp == null
        resultEntity.updateTimestamp == null
        resultEntity.version == null
    }

    def "convert EmployeeEntity to EmployeeDto"() {

        given: "An EmployeeEntity"
        def employeeEntity = EmployeeFixture.validAndPersistedEmployeeEntity()

        when: "convert EmployeeEntity to EmployeeDto"
        def resultDto = serviceUnderTest.entityToDto(employeeEntity)

        then: "no Exception thrown"
        noExceptionThrown()

        and: "resultDto.name has been mapped correctly"
        resultDto.name == employeeEntity.name

        and: "resultDto.id has been mapped correctly"
        resultDto.id == employeeEntity.objectId

    }
}

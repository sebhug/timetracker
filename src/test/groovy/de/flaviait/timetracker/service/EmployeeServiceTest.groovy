package de.flaviait.timetracker.service

import de.flaviait.timetracker.entity.EmployeeEntity
import de.flaviait.timetracker.exception.EmployeeNotFoundException
import de.flaviait.timetracker.fixture.EmployeeFixture
import de.flaviait.timetracker.repository.EmployeeRepository
import spock.lang.Specification

class EmployeeServiceTest extends Specification {

    def employeeRepositoryMock = Mock(EmployeeRepository)

    EmployeeService serviceUnderTest

    def setup() {
        serviceUnderTest = new EmployeeService(employeeRepositoryMock)
    }

    def "create new employee"() {

        given: "EmployeeEntity ready for creation"
        def request = EmployeeFixture.validEmployeeEntityForCreation()

        when: "create new employee"
        def result = serviceUnderTest.createNewEmployee(request)

        then: "no exception thrown"
        noExceptionThrown()

        and: "objectId has been generated and saved in DB"
        1 * employeeRepositoryMock.save({
            it.objectId != null
        } as EmployeeEntity) >> {
            args -> args.get(0)
        }

        and: "created employee returned"
        result.objectId != null
        result.name == request.name
    }

    def "get employee by objectId - successful"() {

        given: "An existing employee"
        def employee = EmployeeFixture.validAndPersistedEmployeeEntity()

        when: "get employee by objectId"
        def result = serviceUnderTest.getEmployee(employee.objectId)

        then: "no exception thrown"
        noExceptionThrown()

        and: "ask DB for Employee"
        1 * employeeRepositoryMock.findByObjectId(employee.objectId) >> Optional.of(employee)

        and: "return employee"
        result.get() == employee
    }

    def "get employee by objectId - not found"() {

        when: "get employee by objectId"
        def result = serviceUnderTest.getEmployee("UNKOWN_OBJECT_ID")

        then: "ask DB for Employee"
        1 * employeeRepositoryMock.findByObjectId(_ as String) >> Optional.empty()

        and: "Result is empty"
        result == Optional.empty()
    }

    def "delete employee - successful"() {

        given: "An existing and persisted employee"
        def employee = EmployeeFixture.validAndPersistedEmployeeEntity()

        when: "delete employee"
        serviceUnderTest.deleteEmployee(employee.objectId)

        then: "ask DB for Employee"
        1 * employeeRepositoryMock.findByObjectId(employee.objectId) >> Optional.of(employee)

        and: "delete employee"
        1 * employeeRepositoryMock.delete(employee)
    }

    def "delete employee - not found"() {

        when: "delete employee by objectId"
        serviceUnderTest.deleteEmployee("UNKOWN_OBJECT_ID")

        then: "ask DB for employee"
        1 * employeeRepositoryMock.findByObjectId(_ as String) >> Optional.empty()

        and: "Employee not found exception"
        thrown(EmployeeNotFoundException)
    }

}

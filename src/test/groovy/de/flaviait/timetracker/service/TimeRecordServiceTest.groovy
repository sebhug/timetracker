package de.flaviait.timetracker.service

import de.flaviait.timetracker.entity.TimeRecordEntity
import de.flaviait.timetracker.exception.EmployeeNotFoundException
import de.flaviait.timetracker.fixture.TimeRecordFixture
import de.flaviait.timetracker.repository.TimeRecordRepository
import spock.lang.Specification

class TimeRecordServiceTest extends Specification {

    def timeRecordRepositoryMock = Mock(TimeRecordRepository)
    def employeeServiceMock = Mock(EmployeeService)

    TimeRecordService serviceUnderTest

    def setup() {
        serviceUnderTest = new TimeRecordService(timeRecordRepositoryMock, employeeServiceMock)
    }

    def "create new TimeRecord"() {

        given: "A valid TimeRecord ready for creation"
        def request = TimeRecordFixture.validTimeRecordEntityReadyForCreation()

        and: "An existing Employee"
        def employee = request.getEmployee()

        when: "create new time record"
        def result = serviceUnderTest.createTimeRecord(request)

        then: "no exception thrown"
        noExceptionThrown()

        and: "ask EmployeeService for existing Employee"
        1 * employeeServiceMock.getEmployee(employee.objectId) >> Optional.of(employee)

        and: "objectId has been generated and saved in DB"

        1 * timeRecordRepositoryMock.save({
            it.objectId != null
        } as TimeRecordEntity) >> {
            args -> args.get(0)
        }

        and: "created timeRecord returned"
        result.objectId != null

    }

    def "create new TimeRecord - Employee Reference does not exits" (){

        given: "A valid TimeRecord ready for creation"
        def request = TimeRecordFixture.validTimeRecordEntityReadyForCreation()

        when: "create new time record"
        serviceUnderTest.createTimeRecord(request)

        then: "ask EmployeeService for existing Employee - returns not found"
        1 * employeeServiceMock.getEmployee(_ as String) >> Optional.empty()

        and: "EmployeeNotFound Exception"
        thrown(EmployeeNotFoundException)

    }

    def "get timeRecord by objectId - successful"() {

        given: "An existing timeRecord"
        def timeRecord = TimeRecordFixture.validAndPersistedTimeRecordEntity()

        when: "get timeRecord by objectId"
        def result = serviceUnderTest.getTimeRecord(timeRecord.objectId)

        then: "no exception thrown"
        noExceptionThrown()

        and: "ask DB for TimeRecord"
        1 * timeRecordRepositoryMock.findByObjectId(timeRecord.objectId) >> Optional.of(timeRecord)

        and: "return timerecord"
        result.get() == timeRecord
    }

    def "update timeRecord"() {

        given: "An existing timeRecord"
        def timeRecord = TimeRecordFixture.validAndPersistedTimeRecordEntity()

        and: "An update for this TimeRecord"
        def update = new TimeRecordEntity(
                objectId: timeRecord.objectId,
                employee: timeRecord.employee,
                recordDate: timeRecord.recordDate,
                minutes: timeRecord.minutes,
                description: "This is an update"
        )

        when: "updating timeRecord"
        def result = serviceUnderTest.updateTimeRecord(update)

        then: "find timeRecord in DB"
        1 * timeRecordRepositoryMock.findByObjectId(_ as String) >> Optional.of(timeRecord)

        and: "update timeRecord in DB"
        1 * timeRecordRepositoryMock.save({
            it.objectId == timeRecord.objectId
            it.employee == timeRecord.employee
            it.description == update.description
        } as TimeRecordEntity) >> timeRecord

        and: "updated timeRecord returned"
        result != null
    }

}

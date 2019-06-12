package de.flaviait.timetracker.rest

import spock.lang.Ignore
import spock.lang.Specification

class TimesheetRestControllerTest extends Specification {

    @Ignore
    def "get monthly timesheet"() {
        given: "an existing Employee"

        and: "4 Time Records in Month april with total 4 hours of Work"

        when: "Get the april timesheet for employee via Rest"

        then: "status is 200 OK"

        and: "total working hours for april are 4h"

    }

}

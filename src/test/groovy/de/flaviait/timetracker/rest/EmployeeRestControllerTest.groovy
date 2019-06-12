package de.flaviait.timetracker.rest

import com.fasterxml.jackson.databind.ObjectMapper
import de.flaviait.timetracker.dto.EmployeeDto
import de.flaviait.timetracker.entity.EmployeeEntity
import de.flaviait.timetracker.fixture.EmployeeFixture
import de.flaviait.timetracker.mapper.EmployeeMapper
import de.flaviait.timetracker.service.EmployeeService
import groovy.json.JsonOutput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [EmployeeRestController.class])
class EmployeeRestControllerTest extends Specification {

    @Autowired
    MockMvc mockMvc

    @Autowired
    EmployeeService employeeService

    @Autowired
    EmployeeMapper employeeMapper

    @Autowired
    ObjectMapper objectMapper


    def "create new employee"() {

        given: "a valid EmployeeDto"
        def request = EmployeeFixture.validEmployeeDtoForCreation()

        and: "mocked EmployeeMapper"
        employeeMapper.dtoToEntity(_ as EmployeeDto) >> new EmployeeEntity()

        and: "mocked EmployeeService"
        employeeService.createNewEmployee(_ as EmployeeEntity) >> EmployeeFixture.validAndPersistedEmployeeEntity()

        when: "creating employee via rest"
        def result = mockMvc
                .perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(JsonOutput.toJson(request)))

        then: "response status is 201 CREATED"
        result.andExpect(status().isCreated())
    }

    @TestConfiguration
    static class StubConfig {

        DetachedMockFactory detachedMockFactory = new DetachedMockFactory()

        @Bean
        EmployeeService employeeService() {
            return detachedMockFactory.Mock(EmployeeService)
        }

        @Bean
        EmployeeMapper employeeMapper() {
            return detachedMockFactory.Mock(EmployeeMapper)
        }
    }

}

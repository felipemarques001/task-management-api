package com.felipe.taskmanagementeapi.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felipe.taskmanagementeapi.Repositories.EmployeeRepository;
import com.felipe.taskmanagementeapi.Repositories.TeamRepository;
import com.felipe.taskmanagementeapi.dtos.EmployeeDto;
import com.felipe.taskmanagementeapi.entities.EmployeeEntity;
import com.felipe.taskmanagementeapi.entities.TeamEntity;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EmployeeControllerITest {

    private static final String TEAM_NAME = "teamNameTest";
    private static final String EMPLOYEE_FIRST_NAME = "firstNameTest";
    private static final String EMPLOYEE_LAST_NAME = "lastNameTest";
    private static final String EMPLOYEE_ROLE = "roleTest";
    private static final String EMPLOYEE_NEW_FIRST_NAME = "firstNameTest";
    private static final String EMPLOYEE_NEW_LAST_NAME = "lastNameTest";
    private static final String EMPLOYEE_NEW_ROLE = "roleTest";
    private static final Integer INVALID_ID = 1;
    private static final String TEAM_NOT_FOUND_ERROR_MESSAGE = "Team not found with id : " + INVALID_ID;
    private static final String EMPLOYEE_NOT_FOUND_ERROR_MESSAGE = "Employee not found with id : " + INVALID_ID;
    private static final String ERROR_DETAILS_URL = "uri=/employee";
    private static final String FIRST_NAME_ERROR_MESSAGE = "First name cannot be empty!";
    private static final String LAST_NAME_ERROR_MESSAGE = "Last name cannot be empty!";
    private static final String ROLE_ERROR_MESSAGE = "Role cannot be empty!";
    private static final String TEAM_ID_ERROR_MESSAGE = "Inform the team id in which this employee is working!";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
    }

    @DisplayName("Integration test for createEmployee method - success case")
    @Test
    void givenEmployeeDtoAndSavedTeam_whenCreateEmployee_thenReturnSavedEmployee() throws Exception {
        // given
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(TEAM_NAME);
        teamRepository.save(teamEntity);

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName(EMPLOYEE_FIRST_NAME);
        employeeDto.setLastName(EMPLOYEE_LAST_NAME);
        employeeDto.setRole(EMPLOYEE_ROLE);
        employeeDto.setTeamId(teamEntity.getId());

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employeeDto)));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.firstName", CoreMatchers.is(EMPLOYEE_FIRST_NAME)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.lastName", CoreMatchers.is(EMPLOYEE_LAST_NAME)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.role", CoreMatchers.is(EMPLOYEE_ROLE)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.teamId", CoreMatchers.is(teamEntity.getId())));
    }

    @DisplayName("Integration test for createEmployee method - fail case - invalid team id")
    @Test
    void givenInvalidTeamId_whenCreateEmployee_thenResourceNotFoundAndErrorDetails() throws Exception {
        // given
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName(EMPLOYEE_FIRST_NAME);
        employeeDto.setLastName(EMPLOYEE_LAST_NAME);
        employeeDto.setRole(EMPLOYEE_ROLE);
        employeeDto.setTeamId(INVALID_ID);

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employeeDto)));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", CoreMatchers.is(TEAM_NOT_FOUND_ERROR_MESSAGE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.details", CoreMatchers.is(ERROR_DETAILS_URL)));
    }

    @DisplayName("Integration test for createEmployee method - fail case - invalid employeeDto")
    @Test
    void givenInvalidEmployeeDto_whenCreateEmployee_thenBadRequestAndErrorDetails() throws Exception {
        // given
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(TEAM_NAME);
        teamRepository.save(teamEntity);

        EmployeeDto employeeDto = new EmployeeDto();

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employeeDto)));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.firstName", CoreMatchers.is(FIRST_NAME_ERROR_MESSAGE)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.lastName", CoreMatchers.is(LAST_NAME_ERROR_MESSAGE)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.role", CoreMatchers.is(ROLE_ERROR_MESSAGE)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.teamId", CoreMatchers.is(TEAM_ID_ERROR_MESSAGE)));
    }

    @DisplayName("Integration test for findEmployeeById method - success case")
    @Test
    void givenValidId_whenFindEmployeeById_thenReturnSavedEmployee() throws Exception {
        // given
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(TEAM_NAME);
        teamRepository.save(teamEntity);

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName(EMPLOYEE_FIRST_NAME);
        employeeEntity.setLastName(EMPLOYEE_LAST_NAME);
        employeeEntity.setRole(EMPLOYEE_ROLE);
        employeeEntity.setTeam(teamEntity);
        employeeRepository.save(employeeEntity);

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .get("/employee/{id}", employeeEntity.getId()));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.id", CoreMatchers.is(employeeEntity.getId())))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.firstName", CoreMatchers.is(EMPLOYEE_FIRST_NAME)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.lastName", CoreMatchers.is(EMPLOYEE_LAST_NAME)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.role", CoreMatchers.is(EMPLOYEE_ROLE)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.teamId", CoreMatchers.is(teamEntity.getId())));
    }

    @DisplayName("Integration test for findEmployeeById method - fail case")
    @Test
    void givenInvalidId_whenFindEmployeeById_thenResourceNotFoundAndErrorDetails() throws Exception {
        // given
        // nothing

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .get("/employee/{id}", INVALID_ID));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", CoreMatchers.is(EMPLOYEE_NOT_FOUND_ERROR_MESSAGE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.details", CoreMatchers.is(ERROR_DETAILS_URL + "/" + INVALID_ID)));
    }

    @DisplayName("Integration test for findAllEmployees method")
    @Test
    void givenSavedEmployee_whenFindAllEmployees_thenReturnEmployeeDtoList() throws Exception {
        // given
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(TEAM_NAME);
        teamRepository.save(teamEntity);

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName(EMPLOYEE_FIRST_NAME);
        employeeEntity.setLastName(EMPLOYEE_LAST_NAME);
        employeeEntity.setRole(EMPLOYEE_ROLE);
        employeeEntity.setTeam(teamEntity);
        employeeRepository.save(employeeEntity);

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .get("/employee"));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json("[{id:" + employeeEntity.getId() + "," +
                                "firstName:" + EMPLOYEE_FIRST_NAME + "," +
                                "lastName:" + EMPLOYEE_LAST_NAME + "," +
                                "role:" + EMPLOYEE_ROLE + "," +
                                "teamId:" + teamEntity.getId() + "}]"));
    }

    @DisplayName("Integration test for updateEmployee method - success case")
    @Test
    void givenNewEmployeeDto_whenUpdateEmployee_thenReturnUpdatedEmployee() throws Exception {
        // given
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(TEAM_NAME);
        teamRepository.save(teamEntity);

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName(EMPLOYEE_FIRST_NAME);
        employeeEntity.setLastName(EMPLOYEE_LAST_NAME);
        employeeEntity.setRole(EMPLOYEE_ROLE);
        employeeEntity.setTeam(teamEntity);
        employeeRepository.save(employeeEntity);

        EmployeeDto newEmployeeDto = new EmployeeDto();
        newEmployeeDto.setFirstName(EMPLOYEE_NEW_FIRST_NAME);
        newEmployeeDto.setLastName(EMPLOYEE_NEW_LAST_NAME);
        newEmployeeDto.setRole(EMPLOYEE_NEW_ROLE);
        newEmployeeDto.setTeamId(teamEntity.getId());

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                        .put("/employee/{id}", employeeEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newEmployeeDto)));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.id", CoreMatchers.is(employeeEntity.getId())))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.firstName", CoreMatchers.is(EMPLOYEE_NEW_FIRST_NAME)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.lastName", CoreMatchers.is(EMPLOYEE_NEW_LAST_NAME)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.role", CoreMatchers.is(EMPLOYEE_NEW_ROLE)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.teamId", CoreMatchers.is(employeeEntity.getTeam().getId())));
    }

    @DisplayName("Integration test for updateEmployee method - fail case - Invalid Id")
    @Test
    void givenInvalidId_whenUpdateEmployee_thenResourceNotFoundAndErrorDetails() throws Exception {
        // given
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(TEAM_NAME);
        teamRepository.save(teamEntity);

        EmployeeDto newEmployeeDto = new EmployeeDto();
        newEmployeeDto.setFirstName(EMPLOYEE_NEW_FIRST_NAME);
        newEmployeeDto.setLastName(EMPLOYEE_NEW_LAST_NAME);
        newEmployeeDto.setRole(EMPLOYEE_NEW_ROLE);
        newEmployeeDto.setTeamId(teamEntity.getId());

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .put("/employee/{id}", INVALID_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newEmployeeDto)));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", CoreMatchers.is(EMPLOYEE_NOT_FOUND_ERROR_MESSAGE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.details", CoreMatchers.is(ERROR_DETAILS_URL + "/" + INVALID_ID)));
    }

    @DisplayName("Integration test for updateEmployee method - fail case - invalid employeeDto")
    @Test
    void givenInvalidEmployeeDto_whenUpdateEmployee_thenReturnBadRequestAndErrorDetails() throws Exception {
        // given
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(TEAM_NAME);
        teamRepository.save(teamEntity);

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName(EMPLOYEE_FIRST_NAME);
        employeeEntity.setLastName(EMPLOYEE_LAST_NAME);
        employeeEntity.setRole(EMPLOYEE_LAST_NAME);
        employeeEntity.setTeam(teamEntity);
        employeeRepository.save(employeeEntity);

        EmployeeDto newEmployeeDto = new EmployeeDto();

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .put("/employee/{id}", employeeEntity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newEmployeeDto)));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.firstName", CoreMatchers.is(FIRST_NAME_ERROR_MESSAGE)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.lastName", CoreMatchers.is(LAST_NAME_ERROR_MESSAGE)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.role", CoreMatchers.is(ROLE_ERROR_MESSAGE)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.teamId", CoreMatchers.is(TEAM_ID_ERROR_MESSAGE)));
    }

    @DisplayName("Integration test for deleteEmployeeById method - success case")
    @Test
    void givenValidId_whenDeleteEmployeeById_thenReturnOkAndSuccessString() throws Exception {
        // given
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(TEAM_NAME);
        teamRepository.save(teamEntity);

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName(EMPLOYEE_FIRST_NAME);
        employeeEntity.setLastName(EMPLOYEE_LAST_NAME);
        employeeEntity.setRole(EMPLOYEE_ROLE);
        employeeEntity.setTeam(teamEntity);
        employeeRepository.save(employeeEntity);

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .delete("/employee/{id}", employeeEntity.getId()));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("Integration test for deleteEmployee method - fail case")
    @Test
    void givenInvalidId_whenDeleteEmployeeById_thenReturnResourceNotFoundAndErrorDetails() throws Exception {
        // given
        // nothing

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .delete("/employee/{id}", INVALID_ID));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", CoreMatchers.is(EMPLOYEE_NOT_FOUND_ERROR_MESSAGE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.details", CoreMatchers.is(ERROR_DETAILS_URL + "/" + INVALID_ID)));
    }
}

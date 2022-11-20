package com.felipe.taskmanagementeapi.repositories;

import com.felipe.taskmanagementeapi.Repositories.EmployeeRepository;
import com.felipe.taskmanagementeapi.entities.EmployeeEntity;
import com.felipe.taskmanagementeapi.entities.TeamEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class EmployeeRepositoryTest {

    private static final String FIRST_NAME_EMPLOYEE1 = "Felipe";
    private static final String LAST_NAME_EMPLOYEE1 = "Marques";
    private static final String ROLE_EMPLOYEE1 = "Back-end developer";
    private static final Integer TEAM_ID_EMPLOYEE1 = 1;

    private static final String FIRST_NAME_EMPLOYEE2 = "Vanessa";
    private static final String LAST_NAME_EMPLOYEE2 = "Dias";
    private static final String ROLE_EMPLOYEE2 = "Front-end developer";
    private static final Integer TEAM_ID_EMPLOYEE2 = 2;


    @Autowired
    private EmployeeRepository employeeRepository;

    private EmployeeEntity employeeEntity;
    private TeamEntity teamEntity;

    @BeforeEach
    void setUp() {
        teamEntity = new TeamEntity();
        teamEntity.setName("Nome do time_teste");

        employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName(FIRST_NAME_EMPLOYEE1);
        employeeEntity.setLastName(LAST_NAME_EMPLOYEE1);
        employeeEntity.setRole(ROLE_EMPLOYEE1);
        employeeEntity.setTeam(teamEntity);
    }

    @DisplayName("Unit test for save method")
    @Test
    public void givenEmployeeEntity_whenSaveEmployee_thenReturnSavedEmployee() {
        // given
        // Code in setUp method

        // when
        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);

        // then
        assertNotNull(savedEmployee.getId());
        assertEquals(FIRST_NAME_EMPLOYEE1, savedEmployee.getFirstName());
        assertEquals(LAST_NAME_EMPLOYEE1, savedEmployee.getLastName());
        assertEquals(ROLE_EMPLOYEE1, savedEmployee.getRole());
        assertEquals(teamEntity, savedEmployee.getTeam());
    }
}

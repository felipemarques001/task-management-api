package com.felipe.taskmanagementeapi.repositories;

import com.felipe.taskmanagementeapi.Repositories.EmployeeRepository;
import com.felipe.taskmanagementeapi.entities.EmployeeEntity;
import com.felipe.taskmanagementeapi.entities.TeamEntity;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class EmployeeRepositoryTest {

    private static final String FIRST_NAME_EMPLOYEE1 = "Felipe";
    private static final String LAST_NAME_EMPLOYEE1 = "Marques";
    private static final String ROLE_EMPLOYEE1 = "Back-end developer";
    private static final Long TEAM_ID_EMPLOYEE1 = 1L;

    private static final String FIRST_NAME_EMPLOYEE2 = "Vanessa";
    private static final String LAST_NAME_EMPLOYEE2 = "Dias";
    private static final String ROLE_EMPLOYEE2 = "Front-end developer";
    private static final Long TEAM_ID_EMPLOYEE2 = 2L;


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
}

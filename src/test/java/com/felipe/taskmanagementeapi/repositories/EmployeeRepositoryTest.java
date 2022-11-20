package com.felipe.taskmanagementeapi.repositories;

import com.felipe.taskmanagementeapi.Repositories.EmployeeRepository;
import com.felipe.taskmanagementeapi.Repositories.TeamRepository;
import com.felipe.taskmanagementeapi.entities.EmployeeEntity;
import com.felipe.taskmanagementeapi.entities.TeamEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

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

    @Autowired
    private TeamRepository teamRepository;

    private EmployeeEntity employeeEntity;
    private TeamEntity teamEntity;

    @BeforeEach
    void setUp() {
        teamEntity = new TeamEntity();
        teamEntity.setName("Nome do time_teste");
        teamRepository.save(teamEntity);

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

    @DisplayName("Unit test for findAll method")
    @Test
    public void givenSavedEmployees_whenFindAll_thenReturnEmployeeList() {
        // given
        EmployeeEntity employeeEntity2 = new EmployeeEntity();
        employeeEntity2.setFirstName(FIRST_NAME_EMPLOYEE2);
        employeeEntity2.setLastName(LAST_NAME_EMPLOYEE2);
        employeeEntity2.setRole(ROLE_EMPLOYEE2);
        employeeEntity2.setTeam(teamEntity);

        employeeRepository.saveAll(List.of(employeeEntity, employeeEntity2));

        // when
        List<EmployeeEntity> list = employeeRepository.findAll();

        // then
        assertNotNull(list);
        assertEquals(2, list.size());

        assertEquals(FIRST_NAME_EMPLOYEE1, list.get(0).getFirstName());
        assertEquals(LAST_NAME_EMPLOYEE1, list.get(0).getLastName());
        assertEquals(ROLE_EMPLOYEE1, list.get(0).getRole());
        assertEquals(teamEntity, list.get(0).getTeam());

        assertEquals(FIRST_NAME_EMPLOYEE2, list.get(1).getFirstName());
        assertEquals(LAST_NAME_EMPLOYEE2, list.get(1).getLastName());
        assertEquals(ROLE_EMPLOYEE2, list.get(1).getRole());
        assertEquals(teamEntity, list.get(1).getTeam());
    }

    @DisplayName("Unit test for findById method")
    @Test
    public void givenEmployeeId_whenFindById_thenReturnOptionalEmployee() {
        // given
        employeeRepository.save(employeeEntity);

        // when
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(employeeEntity.getId());

        // then
        assertFalse(optionalEmployee.isEmpty());
        assertNotNull(optionalEmployee.get().getId());
        assertEquals(FIRST_NAME_EMPLOYEE1, optionalEmployee.get().getFirstName());
        assertEquals(LAST_NAME_EMPLOYEE1, optionalEmployee.get().getLastName());
        assertEquals(ROLE_EMPLOYEE1, optionalEmployee.get().getRole());
        assertEquals(teamEntity, optionalEmployee.get().getTeam());
    }

    @DisplayName("Unit test for flow to update a EmployeeEntity")
    @Test
    public void givenNewEmployee_whenUpdate_thenReturnUpdatedEmployee() {
        // given
        employeeRepository.save(employeeEntity);
        EmployeeEntity savedEmployee = employeeRepository.findById(employeeEntity.getId()).get();
        savedEmployee.setFirstName(FIRST_NAME_EMPLOYEE2);
        savedEmployee.setLastName(LAST_NAME_EMPLOYEE2);
        savedEmployee.setRole(ROLE_EMPLOYEE2);

        // when
        EmployeeEntity updatedEmployee = employeeRepository.save(savedEmployee);

        // then
        assertNotNull(updatedEmployee);
        assertEquals(FIRST_NAME_EMPLOYEE2, updatedEmployee.getFirstName());
        assertEquals(LAST_NAME_EMPLOYEE2, updatedEmployee.getLastName());
        assertEquals(ROLE_EMPLOYEE2, updatedEmployee.getRole());
        assertEquals(teamEntity, updatedEmployee.getTeam());
    }
}

package com.felipe.taskmanagementeapi.controllers;

import com.felipe.taskmanagementeapi.dtos.EmployeeDto;
import com.felipe.taskmanagementeapi.services.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeControllerTest {

    private static final Integer ID_EMPLOYEE = 1;
    private static final String FIRST_NAME_EMPLOYEE = "Felipe";
    private static final String LAST_NAME_EMPLOYEE = "Marques";
    private static final String ROLE_EMPLOYEE = "Back-end developer";
    private static final Integer ID_TEAM = 1;

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeServiceImpl employeeService;

    private EmployeeDto savedEmployee;
    private EmployeeDto requestEmployeeDto;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        savedEmployee = new EmployeeDto();
        savedEmployee.setId(ID_EMPLOYEE);
        savedEmployee.setFirstName(FIRST_NAME_EMPLOYEE);
        savedEmployee.setLastName(LAST_NAME_EMPLOYEE);
        savedEmployee.setRole(ROLE_EMPLOYEE);

        requestEmployeeDto = new EmployeeDto();
        savedEmployee.setFirstName(FIRST_NAME_EMPLOYEE);
        savedEmployee.setLastName(LAST_NAME_EMPLOYEE);
        savedEmployee.setRole(ROLE_EMPLOYEE);
        savedEmployee.setTeamId(ID_TEAM);
    }

    @DisplayName("Unit test for createEmployee method")
    @Test
    void givenRequestEmployeeDto_whenCreateEmployee_thenReturnSavedEmployee() {
        // given
        Mockito.when(employeeService.createEmployee(Mockito.any(EmployeeDto.class))).thenReturn(savedEmployee);

        // when
        ResponseEntity<EmployeeDto> response = employeeController.createEmployee(requestEmployeeDto);

        // then
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(EmployeeDto.class, response.getBody().getClass());
        assertEquals(ID_EMPLOYEE, response.getBody().getId());
        assertEquals(FIRST_NAME_EMPLOYEE, response.getBody().getFirstName());
        assertEquals(LAST_NAME_EMPLOYEE, response.getBody().getLastName());
        assertEquals(ROLE_EMPLOYEE, response.getBody().getRole());
    }

    @DisplayName("Unit test for findEmployeeById method")
    @Test
    void givenValidId_whenFindEmployeeById_thenReturnSavedEmployee() {
        // given
        Mockito.when(employeeService.findEmployeeById(Mockito.anyInt())).thenReturn(savedEmployee);

        // when
        ResponseEntity<EmployeeDto> response = employeeController.findEmployeeById(ID_EMPLOYEE);

        // then
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(EmployeeDto.class, response.getBody().getClass());
        assertEquals(ID_EMPLOYEE, response.getBody().getId());
        assertEquals(FIRST_NAME_EMPLOYEE, response.getBody().getFirstName());
        assertEquals(LAST_NAME_EMPLOYEE, response.getBody().getLastName());
        assertEquals(ROLE_EMPLOYEE, response.getBody().getRole());
    }

    @DisplayName("Unit test for findAllEmployees method")
    @Test
    void givenSavedEmployeeList_whenFindAllEmployees_thenReturnListEmployeeDto() {
        // given
        Mockito.when(employeeService.findAllEmployees()).thenReturn(List.of(savedEmployee));

        // when
        ResponseEntity<List<EmployeeDto>> response = employeeController.findAllEmployees();

        // then
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(EmployeeDto.class, response.getBody().get(0).getClass());
        assertEquals(ID_EMPLOYEE, response.getBody().get(0).getId());
        assertEquals(FIRST_NAME_EMPLOYEE, response.getBody().get(0).getFirstName());
        assertEquals(LAST_NAME_EMPLOYEE, response.getBody().get(0).getLastName());
        assertEquals(ROLE_EMPLOYEE, response.getBody().get(0).getRole());
    }

    @DisplayName("Unit test for updateEmployee method")
    @Test
    void givenUpdatedEmployeeDto_whenUpdateEmployee_thenReturnNewEmployeeDto() {
        // given
        String newFirstName = "newFirstName";
        String newLastName = "newLastName";
        String newRole = "newRole";

        savedEmployee.setFirstName(newFirstName);
        savedEmployee.setLastName(newLastName);
        savedEmployee.setRole(newRole);

        requestEmployeeDto.setFirstName(newFirstName);
        requestEmployeeDto.setLastName(newLastName);
        requestEmployeeDto.setRole(newRole);

        Mockito.when(employeeService.updateEmployee(Mockito.any(EmployeeDto.class), Mockito.anyInt())).thenReturn(savedEmployee);

        // when
        ResponseEntity<EmployeeDto> response = employeeController.updateEmployee(requestEmployeeDto, ID_EMPLOYEE);

        // then
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(EmployeeDto.class, response.getBody().getClass());
        assertEquals(ID_EMPLOYEE, response.getBody().getId());
        assertEquals(newFirstName, response.getBody().getFirstName());
        assertEquals(newLastName, response.getBody().getLastName());
        assertEquals(newRole, response.getBody().getRole());
    }

    @DisplayName("Unit test for deleteEmployeeById method")
    @Test
    void givenValidId_whenDeleteEmployeeById_thenReturnSuccessString() {
        // given
        String successString = "Employee successfully deleted!";
        Mockito.when(employeeService.deleteEmployeeById(Mockito.anyInt())).thenReturn(successString);

        // when
        ResponseEntity<String> response = employeeController.deleteEmployeeById(ID_EMPLOYEE);

        // then
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(String.class, response.getBody().getClass());
        assertEquals(successString, response.getBody());
        Mockito.verify(employeeService, Mockito.times(1)).deleteEmployeeById(Mockito.anyInt());
    }
}
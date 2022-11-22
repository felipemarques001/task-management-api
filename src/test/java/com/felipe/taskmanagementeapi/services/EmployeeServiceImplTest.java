package com.felipe.taskmanagementeapi.services;

import com.felipe.taskmanagementeapi.Repositories.EmployeeRepository;
import com.felipe.taskmanagementeapi.Repositories.TeamRepository;
import com.felipe.taskmanagementeapi.dtos.EmployeeDto;
import com.felipe.taskmanagementeapi.entities.EmployeeEntity;
import com.felipe.taskmanagementeapi.entities.TeamEntity;
import com.felipe.taskmanagementeapi.exceptions.ResourceNotFoundException;
import com.felipe.taskmanagementeapi.services.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class EmployeeServiceImplTest {

    private static final Integer ID_EMPLOYEE = 1;
    private static final String FIRST_NAME_EMPLOYEE = "Felipe";
    private static final String LAST_NAME_EMPLOYEE = "Marques";
    private static final String ROLE_EMPLOYEE = "Back-end developer";

    private static final String NAME_TEAM = "Departamento de TI";
    private static final Integer ID_TEAM = 1;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private TeamRepository teamRepository;

    private EmployeeEntity savedEmployee;
    private EmployeeDto employeeDto;
    private TeamEntity savedTeam;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        savedTeam = new TeamEntity();
        savedTeam.setId(ID_TEAM);
        savedTeam.setName(NAME_TEAM);

        savedEmployee = new EmployeeEntity();
        savedEmployee.setId(ID_EMPLOYEE);
        savedEmployee.setFirstName(FIRST_NAME_EMPLOYEE);
        savedEmployee.setLastName(LAST_NAME_EMPLOYEE);
        savedEmployee.setRole(ROLE_EMPLOYEE);
        savedEmployee.setTeam(savedTeam);

        employeeDto = new EmployeeDto();
        employeeDto.setFirstName(FIRST_NAME_EMPLOYEE);
        employeeDto.setLastName(LAST_NAME_EMPLOYEE);
        employeeDto.setRole(ROLE_EMPLOYEE);
        employeeDto.setTeamId(ID_TEAM);
    }

    @DisplayName("Unit test for createEmployee method - success case")
    @Test
    void givenEmployeeDto_whenCreateEmployee_thenReturnSavedEmployee() {
        // given
        Mockito.when(teamRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(savedTeam));
        Mockito.when(employeeRepository.save(Mockito.any(EmployeeEntity.class))).thenReturn(savedEmployee);

        // when
        EmployeeDto response = employeeService.createEmployee(employeeDto);

        // then
        assertNotNull(response);
        assertEquals(ID_EMPLOYEE, response.getId());
        assertEquals(FIRST_NAME_EMPLOYEE, response.getFirstName());
        assertEquals(LAST_NAME_EMPLOYEE, response.getLastName());
        assertEquals(ROLE_EMPLOYEE, response.getRole());
        assertEquals(ID_TEAM, response.getTeamId());
    }

    @DisplayName("Unit test for createEmployee method - fail case")
    @Test
    void givenInvalidTeamId_whenCreateEmployee_thenThrowsResourceNotFoundException() {
        // given
        Mockito.when(teamRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        // when
        try {
            EmployeeDto response = employeeService.createEmployee(employeeDto);
        } catch (Exception ex) {
            // then
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("Team not found with id : 1", ex.getMessage());
        }
    }

    @DisplayName("Unit test for findEmployeeById method - success case")
    @Test
    void givenValidId_whenFindEmployeeById_thenReturnSavedEmployee() {
        // given
        Mockito.when(employeeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(savedEmployee));

        // when
        EmployeeDto response = employeeService.findEmployeeById(ID_EMPLOYEE);

        // then
        assertNotNull(response);
        assertEquals(ID_EMPLOYEE, response.getId());
        assertEquals(FIRST_NAME_EMPLOYEE, response.getFirstName());
        assertEquals(LAST_NAME_EMPLOYEE, response.getLastName());
        assertEquals(ROLE_EMPLOYEE, response.getRole());
    }

    @DisplayName("Unit test for findEmployeeById method - fail case")
    @Test
    void givenInvalidTeamId_whenFindEmployeeById_thenThrowsResourceNotFoundException() {
        // given
        Mockito.when(employeeRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        // when
        try {
            EmployeeDto response = employeeService.findEmployeeById(ID_EMPLOYEE);
        } catch (Exception ex) {
            // then
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("Employee not found with id : 1", ex.getMessage());
        }
    }

    @DisplayName("Unit test for findAllEmployees method")
    @Test
    void givenEmployeeEntityList_whenFindAllEmployees_thenReturnEmployeeDtoList() {
        // given
        Mockito.when(employeeRepository.findAll()).thenReturn(List.of(savedEmployee));

        // when
        List<EmployeeDto> response = employeeService.findAllEmployees();

        // then
        assertNotNull(response);
        assertNotNull(response.get(0));
        assertEquals(ID_EMPLOYEE, response.get(0).getId());
        assertEquals(FIRST_NAME_EMPLOYEE, response.get(0).getFirstName());
        assertEquals(LAST_NAME_EMPLOYEE, response.get(0).getLastName());
        assertEquals(ROLE_EMPLOYEE, response.get(0).getRole());
    }

    @DisplayName("Unit test for updateEmployee method - case success")
    @Test
    void givenNewEmployeeDto_whenUpdateEmployee_thenReturnUpdatedEmployee() {
        // given
        String newFirstName = "newFirstNameTest";
        String newLastName = "newLastNameTest";
        String newRole = "roleTest";

        EmployeeDto updatedEmployeeDto = new EmployeeDto();
        updatedEmployeeDto.setFirstName(newFirstName);
        updatedEmployeeDto.setLastName(newLastName);
        updatedEmployeeDto.setRole(newRole);
        updatedEmployeeDto.setTeamId(ID_TEAM);

        Mockito.when(employeeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(savedEmployee));
        Mockito.when(employeeRepository.save(Mockito.any(EmployeeEntity.class))).thenReturn(savedEmployee);

        // when
        EmployeeDto response = employeeService.updateEmployee(updatedEmployeeDto, ID_EMPLOYEE);

        // then
        assertNotNull(response);
        assertEquals(ID_EMPLOYEE, response.getId());
        assertEquals(newFirstName, response.getFirstName());
        assertEquals(newLastName, response.getLastName());
        assertEquals(newRole, response.getRole());
    }

    @DisplayName("Unit test for updateEmployee method - fail case")
    @Test
    void givenInvalidId_whenUpdateEmployee_thenThrowsResourceNotFoundException() {
        // given
        Mockito.when(employeeRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        // I will let newEmployeeDto without values in its attributes, because I won't use them
        EmployeeDto updatedEmployeeDto = new EmployeeDto();

        // when
        try {
            EmployeeDto response = employeeService.updateEmployee(updatedEmployeeDto, ID_EMPLOYEE);
        } catch (Exception ex) {
            // then
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("Employee not found with id : 1", ex.getMessage());
        }
    }

    @DisplayName("Unit test for deleteEmployeeById - success case")
    @Test
    void whenValidId_whenDeleteEmployeeById_thenDeleteWithSuccess() {
        // given
        Mockito.when(employeeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(savedEmployee));
        Mockito.doNothing().when(employeeRepository).deleteById(Mockito.anyInt());

        // when
        String response = employeeService.deleteEmployeeById(ID_EMPLOYEE);

        // then
        assertNotNull(response);
        assertEquals("Employee successfully deleted!", response);
        Mockito.verify(employeeRepository, Mockito.times(1)).deleteById(Mockito.anyInt());
    }

    @DisplayName("Unit test for deleteEmployeeById - fail case")
    @Test
    void whenInvalidId_whenDeleteEmployeeById_thenThrowsResourceNotFoundException() {
        // given
        Mockito.when(employeeRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        // when
        try {
            String response = employeeService.deleteEmployeeById(ID_EMPLOYEE);
        } catch (Exception ex) {
            // then
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("Employee not found with id : 1", ex.getMessage());
        }
    }
}

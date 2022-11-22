package com.felipe.taskmanagementeapi.services;

import com.felipe.taskmanagementeapi.Repositories.EmployeeRepository;
import com.felipe.taskmanagementeapi.Repositories.TeamRepository;
import com.felipe.taskmanagementeapi.dtos.EmployeeDto;
import com.felipe.taskmanagementeapi.entities.EmployeeEntity;
import com.felipe.taskmanagementeapi.entities.TeamEntity;
import com.felipe.taskmanagementeapi.services.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

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
}

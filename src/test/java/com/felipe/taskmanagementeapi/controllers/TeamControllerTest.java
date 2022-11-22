package com.felipe.taskmanagementeapi.controllers;

import com.felipe.taskmanagementeapi.dtos.TeamDto;
import com.felipe.taskmanagementeapi.services.impl.TeamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

@SpringBootTest
public class TeamControllerTest {

    private static final String NAME_TEAM = "Departamento de TI";
    private static final Integer ID_TEAM = 1;

    @InjectMocks
    private TeamController teamController;

    @Mock
    private TeamServiceImpl teamService;

    private TeamDto savedTeamDto;
    private TeamDto teamDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        savedTeamDto = new TeamDto();
        savedTeamDto.setId(ID_TEAM);
        savedTeamDto.setName(NAME_TEAM);
        savedTeamDto.setEmployees(new HashSet<>());
        savedTeamDto.setTasks(new HashSet<>());

        teamDto = new TeamDto();
        teamDto.setName(NAME_TEAM);
    }
}

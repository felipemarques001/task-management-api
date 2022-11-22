package com.felipe.taskmanagementeapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felipe.taskmanagementeapi.dtos.TeamDto;
import com.felipe.taskmanagementeapi.services.impl.TeamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;

@WebMvcTest
public class TeamControllerTest {

    private static final String NAME_TEAM = "Departamento de TI";
    private static final Integer ID_TEAM = 1;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
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

    @DisplayName("Unit test for createTeam method")
    @Test
    public void givenTeamDto_whenCreateTeam_thenReturnSavedTeamDto() {
        // given
        Mockito.when(teamService.createTeam(Mockito.any(TeamDto.class))).thenReturn(savedTeamDto);

        // when

        // then
    }
}

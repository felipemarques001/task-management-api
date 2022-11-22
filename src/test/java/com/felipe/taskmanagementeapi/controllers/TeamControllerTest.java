package com.felipe.taskmanagementeapi.controllers;

import com.felipe.taskmanagementeapi.dtos.TeamDto;
import com.felipe.taskmanagementeapi.services.impl.TeamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TeamControllerTest {

    private static final String NAME_TEAM = "Departamento de TI";
    private static final Integer ID_TEAM = 1;

    @InjectMocks
    private TeamController teamController;

    @Mock
    private TeamServiceImpl teamService;

    private TeamDto savedTeam;
    private TeamDto teamDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        savedTeam = new TeamDto();
        savedTeam.setId(ID_TEAM);
        savedTeam.setName(NAME_TEAM);
        savedTeam.setTasks(new HashSet<>());
        savedTeam.setEmployees(new HashSet<>());

        teamDto = new TeamDto();
        teamDto.setName(NAME_TEAM);
    }

    @DisplayName("Unit test for createTeam method")
    @Test
    void givenTeamDto_whenCreateTeam_thenReturnSavedTeam() {
        // given
        Mockito.when(teamService.createTeam(Mockito.any(TeamDto.class))).thenReturn(savedTeam);

        // when
        ResponseEntity<TeamDto> response = teamController.createTeam(teamDto);

        // then
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(TeamDto.class, response.getBody().getClass());
        assertEquals(ID_TEAM, response.getBody().getId());
        assertEquals(NAME_TEAM, response.getBody().getName());
        assertEquals(0, response.getBody().getEmployees().size());
        assertEquals(0, response.getBody().getTasks().size());
    }

    @DisplayName("Unit test for findTeamById method")
    @Test
    void givenValidId_whenFindTeamById_thenReturnSavedTeam() {
        // given
        Mockito.when(teamService.findTeamById(Mockito.anyInt())).thenReturn(savedTeam);

        // when
        ResponseEntity<TeamDto> response = teamController.findTeamById(ID_TEAM);

        // then
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(TeamDto.class, response.getBody().getClass());
        assertEquals(ID_TEAM, response.getBody().getId());
        assertEquals(NAME_TEAM, response.getBody().getName());
        assertEquals(0, response.getBody().getEmployees().size());
        assertEquals(0, response.getBody().getTasks().size());
    }

    @DisplayName("Unit test for findAllTeams method")
    @Test
    void givenListSavedTeam_whenFindAllTeams_thenReturnListTeamDto() {
        // given
        Mockito.when(teamService.findAllTeams()).thenReturn(List.of(savedTeam));

        // when
        ResponseEntity<List<TeamDto>> response = teamController.findAllTeams();

        // then
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().get(0));
        assertEquals(TeamDto.class, response.getBody().get(0).getClass());
        assertEquals(ID_TEAM, response.getBody().get(0).getId());
        assertEquals(NAME_TEAM, response.getBody().get(0).getName());
        assertEquals(0, response.getBody().get(0).getEmployees().size());
        assertEquals(0, response.getBody().get(0).getTasks().size());
    }

    @DisplayName("Unit test for updateTeamName method")
    @Test
    void givenUpdatedTeamDto_whenUpdateTeamName_thenReturnNewTeamDto() {
        // given
        String newName = "newName";
        teamDto.setName(newName);
        savedTeam.setName(newName);

        Mockito.when(teamService.updateTeamName(Mockito.any(TeamDto.class), Mockito.anyInt())).thenReturn(savedTeam);

        // when
        ResponseEntity<TeamDto> response = teamController.updateNameTeam(teamDto, ID_TEAM);

        // then
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(TeamDto.class, response.getBody().getClass());
        assertEquals(ID_TEAM, response.getBody().getId());
        assertEquals(newName, response.getBody().getName());
        assertEquals(0, response.getBody().getEmployees().size());
        assertEquals(0, response.getBody().getTasks().size());
    }

    @DisplayName("Unit test for deleteTeamById method")
    @Test
    void givenValidId_whenDeleteById_thenReturnSuccessString() {
        // given
        String successString = "Team successfully deleted!";
        Mockito.when(teamService.deleteTeamById(Mockito.anyInt())).thenReturn(successString);

        // when
        ResponseEntity<String> response = teamController.deleteTeamById(ID_TEAM);

        // then
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(String.class, response.getBody().getClass());
        assertEquals(successString, response.getBody());
        Mockito.verify(teamService, Mockito.times(1)).deleteTeamById(Mockito.anyInt());
    }
}
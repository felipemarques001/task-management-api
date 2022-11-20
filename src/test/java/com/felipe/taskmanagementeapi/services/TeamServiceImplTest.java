package com.felipe.taskmanagementeapi.services;

import com.felipe.taskmanagementeapi.Repositories.TeamRepository;
import com.felipe.taskmanagementeapi.dtos.TeamDto;
import com.felipe.taskmanagementeapi.entities.TeamEntity;
import com.felipe.taskmanagementeapi.exceptions.ResourceNotFoundException;
import com.felipe.taskmanagementeapi.services.impl.TeamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TeamServiceImplTest {

    private static final String NAME_TEAM = "Departamento de TI";
    private static final Integer ID_TEAM = 1;
    private static final String NAME_TEAM2 = "Departamento de análise financeira";

    @InjectMocks
    private TeamServiceImpl teamService;

    @Mock
    private TeamRepository teamRepository;

    private TeamEntity savedTeamEntity;
    private TeamDto teamDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        savedTeamEntity = new TeamEntity();
        savedTeamEntity.setId(ID_TEAM);
        savedTeamEntity.setName(NAME_TEAM);
        savedTeamEntity.setEmployees(new HashSet<>());
        savedTeamEntity.setTasks(new HashSet<>());

        teamDto = new TeamDto();
        BeanUtils.copyProperties(savedTeamEntity, teamDto);
    }

    @DisplayName("Unit test for createTeam method")
    @Test
    void givenTeamDto_whenCreateTeam_thenReturnSavedTeam() {
        // given
        Mockito.when(teamRepository.save(Mockito.any(TeamEntity.class))).thenReturn(savedTeamEntity);

        // when
        TeamDto response = teamService.createTeam(teamDto);

        // then
        assertEquals(ID_TEAM, response.getId());
        assertEquals(NAME_TEAM, response.getName());
        assertEquals(0, response.getEmployees().size());
        assertEquals(0, response.getTasks().size());
    }

    @DisplayName("Unit test for findTeamById method - success case")
    @Test
    void givenValidId_whenFindTeamById_thenReturnSavedTeam() {
        // given
        Mockito.when(teamRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(savedTeamEntity));

        // when
        TeamDto response = teamService.findTeamById(ID_TEAM);

        // then
        assertEquals(ID_TEAM, response.getId());
        assertEquals(NAME_TEAM, response.getName());
        assertEquals(0, response.getEmployees().size());
        assertEquals(0, response.getTasks().size());
    }

    @DisplayName("Unit test for findTeamById method - fail case")
    @Test
    void givenInvalidId_whenFindTeamById_thenThrowsResourceNotFoundException() {
        // given
        Mockito.when(teamRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        // when
        try {
            TeamDto response = teamService.findTeamById(ID_TEAM);
        } catch (Exception ex) {
            // then
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("Team not found with id : 1", ex.getMessage());
        }
    }

    @DisplayName("Unit test for findAllTeams method")
    @Test
    void givenTaskEntityList_whenFindAll_thenReturnTaskDtoList() {
        // given
        Mockito.when(teamRepository.findAll()).thenReturn(List.of(savedTeamEntity));

        // when
        List<TeamDto> response = teamService.findAllTeams();

        // then
        assertEquals(ID_TEAM, response.get(0).getId());
        assertEquals(NAME_TEAM, response.get(0).getName());
        assertEquals(0, response.get(0).getEmployees().size());
        assertEquals(0, response.get(0).getTasks().size());
    }

    @DisplayName("Unit test for updateNameTeam method - case success")
    @Test
    void givenNewNameTeam_whenUpdateNameTeam_thenReturnUpdatedTeam() {
        // given
        TeamDto newTeamDto = new TeamDto();
        newTeamDto.setName(NAME_TEAM2);
        Mockito.when(teamRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(savedTeamEntity));
        // As this save return is not being stored in the updateNameTeam method, it is okay to let Mockito return the same savedTeamEntity field.
        Mockito.when(teamRepository.save(Mockito.any(TeamEntity.class))).thenReturn(savedTeamEntity);

        // when
        TeamDto response = teamService.updateTeamName(newTeamDto, ID_TEAM);

        // then
        assertEquals(ID_TEAM, response.getId());
        assertEquals(NAME_TEAM2, response.getName());
        assertEquals(0, response.getEmployees().size());
        assertEquals(0, response.getTasks().size());
    }

    @DisplayName("Unit test for updateNameTeam method - fail case")
    @Test
    void givenInvalidId_whenUpdateNameTeam_thenThrowsResourceNotFoundException() {
        // given
        TeamDto newTeamDto = new TeamDto();
        newTeamDto.setName(NAME_TEAM2);
        Mockito.when(teamRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        try {
            TeamDto response = teamService.updateTeamName(newTeamDto, ID_TEAM);
        } catch (Exception ex) {
            // then
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("Team not found with id : 1", ex.getMessage());
        }
    }

    @DisplayName("Unit test for deleteTeamById - success case")
    @Test
    void whenValidId_whenDeleteTeamById_thenDeleteWithSuccess() {
        // given
        Mockito.when(teamRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(savedTeamEntity));
        Mockito.doNothing().when(teamRepository).deleteById(Mockito.anyInt());

        // when
        String response = teamService.deleteTeamById(ID_TEAM);

        // then
        assertNotNull(response);
        assertEquals("Team successfully deleted!", response);
        Mockito.verify(teamRepository, Mockito.times(1)).deleteById(Mockito.anyInt());
    }

    @DisplayName("Unit test for deleteTeamById - fail case")
    @Test
    void whenInvalidId_whenDeleteTeamById_thenThrowsResourceNotFoundException() {
        // given
        Mockito.when(teamRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        // when
        try {
            String response = teamService.deleteTeamById(ID_TEAM);
        } catch (Exception ex) {
            // then
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("Team not found with id : 1", ex.getMessage());
        }
    }
}

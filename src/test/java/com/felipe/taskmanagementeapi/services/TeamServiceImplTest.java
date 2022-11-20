package com.felipe.taskmanagementeapi.services;

import com.felipe.taskmanagementeapi.Repositories.TeamRepository;
import com.felipe.taskmanagementeapi.dtos.TeamDto;
import com.felipe.taskmanagementeapi.entities.TeamEntity;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}

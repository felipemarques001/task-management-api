package com.felipe.taskmanagementeapi.services;

import com.felipe.taskmanagementeapi.Repositories.TeamRepository;
import com.felipe.taskmanagementeapi.dtos.TeamDto;
import com.felipe.taskmanagementeapi.entities.TeamEntity;
import com.felipe.taskmanagementeapi.services.impl.TeamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.util.HashSet;

public class TeamServiceImplTest {

    private static final String NAME_TEAM = "Departamento de TI";
    private static final String NAME_TEAM2 = "Departamento de análise financeira";

    @InjectMocks
    private TeamServiceImpl teamService;

    @Mock
    private TeamRepository teamRepository;

    private TeamEntity teamEntity;
    private TeamDto teamDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        teamEntity = new TeamEntity();
        teamEntity.setName(NAME_TEAM);
        teamEntity.setEmployees(new HashSet<>());
        teamEntity.setTasks(new HashSet<>());

        teamDto = new TeamDto();
        BeanUtils.copyProperties(teamEntity, teamDto);
    }
}

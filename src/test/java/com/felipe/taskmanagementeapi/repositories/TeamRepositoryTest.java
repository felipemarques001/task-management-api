package com.felipe.taskmanagementeapi.repositories;

import com.felipe.taskmanagementeapi.Repositories.TeamRepository;
import com.felipe.taskmanagementeapi.entities.TeamEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TeamRepositoryTest {

    private static final String NAME_TEAM = "Departamento de TI";
    private static final String NAME_TEAM2 = "Departamento de análise financeira";

    private TeamEntity teamEntity;

    @Autowired
    private TeamRepository teamRepository;

    @BeforeEach
    void setUp() {
        teamEntity = new TeamEntity();
        teamEntity.setName(NAME_TEAM);
        teamEntity.setEmployees(new HashSet<>());
        teamEntity.setTasks(new HashSet<>());
    }

    @DisplayName("Unit test for save method")
    @Test
    public void givenTeamEntity_whenSave_thenReturnSavedTeam() {
        // given
        // code in setUp method

        //when
        TeamEntity savedTeam = teamRepository.save(teamEntity);

        // then
        assertNotNull(savedTeam.getId());
        assertEquals(NAME_TEAM, savedTeam.getName());
        assertEquals(0, savedTeam.getEmployees().size());
        assertEquals(0, savedTeam.getTasks().size());
    }

    @DisplayName("Unit test for findAll method")
    @Test
    public void givenTeamList_whenFindAll_thenReturnSavedTeamList() {
        // given
        TeamEntity teamEntity2 = new TeamEntity();
        teamEntity2.setName(NAME_TEAM2);
        teamEntity2.setEmployees(new HashSet<>());
        teamEntity2.setTasks(new HashSet<>());

        teamRepository.saveAll(List.of(teamEntity, teamEntity2));

        // when
        List<TeamEntity> list = teamRepository.findAll();

        // then
        assertNotNull(list.get(0).getId());
        assertEquals(NAME_TEAM, list.get(0).getName());
        assertEquals(0, list.get(0).getEmployees().size());
        assertEquals(0, list.get(0).getTasks().size());

        assertNotNull(list.get(1).getId());
        assertEquals(NAME_TEAM2, list.get(1).getName());
        assertEquals(0, list.get(1).getEmployees().size());
        assertEquals(0, list.get(1).getTasks().size());
    }

    @DisplayName("Unit test for findById method")
    @Test
    public void givenTeamId_whenFindById_thenReturnOptionalTeam() {
        // given
        teamRepository.save(teamEntity);

        // when
        Optional<TeamEntity> optionalTeam = teamRepository.findById(teamEntity.getId());

        // then
        assertNotNull(optionalTeam);
        assertFalse(optionalTeam.isEmpty());
        assertNotNull(optionalTeam.get().getId());
        assertEquals(NAME_TEAM, optionalTeam.get().getName());
        assertEquals(0, optionalTeam.get().getEmployees().size());
        assertEquals(0, optionalTeam.get().getTasks().size());
    }

    @DisplayName("Unit test for flow to update a TeamEntity")
    @Test
    public void givenNewTeam_whenUpdate_thenReturnUpdatedTeam() {
        // given
        teamRepository.save(teamEntity);
        teamEntity.setName(NAME_TEAM2);

        // when
        TeamEntity savedNewTeam = teamRepository.save(teamEntity);

        // then
        assertNotNull(savedNewTeam.getId());
        assertEquals(teamEntity.getId(), savedNewTeam.getId());
        assertEquals(NAME_TEAM2, savedNewTeam.getName());
        assertEquals(0, savedNewTeam.getEmployees().size());
        assertEquals(0, savedNewTeam.getTasks().size());
    }

    @DisplayName("Unit test for deleteById method")
    @Test
    public void givenTeamId_whenDelete_thenDeleteTeam() {
        // given
        teamRepository.save(teamEntity);

        // when
        teamRepository.deleteById(teamEntity.getId());
        Optional<TeamEntity> optionalTeam = teamRepository.findById(teamEntity.getId());

        // then
        assertTrue(optionalTeam.isEmpty());
    }
}

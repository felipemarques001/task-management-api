package com.felipe.taskmanagementeapi.repositories;

import com.felipe.taskmanagementeapi.Repositories.TeamRepository;
import com.felipe.taskmanagementeapi.entities.TeamEntity;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
    }
}

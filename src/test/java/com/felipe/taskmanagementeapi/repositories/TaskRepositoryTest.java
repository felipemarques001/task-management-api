package com.felipe.taskmanagementeapi.repositories;

import com.felipe.taskmanagementeapi.Repositories.TaskRepository;
import com.felipe.taskmanagementeapi.Repositories.TeamRepository;
import com.felipe.taskmanagementeapi.entities.TaskEntity;
import com.felipe.taskmanagementeapi.entities.TeamEntity;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

@DataJpaTest
public class TaskRepositoryTest {

    private static final String TITLE_TASK1 = "Fazer a unha de Simone";
    private static final String DESCRIPTION_TASK1 = "Unha vermelha cereja";
    private static final Boolean DONE_TASK1 = false;
    private static final LocalDateTime FINALIZATION_DATE_TASK1 = LocalDateTime.of(LocalDateTime.now().getYear(), 12, 12, 0, 0);

    private static final String TITLE_TASK2 = "Fazer a matrícula na faculdade";
    private static final String DESCRIPTION_TASK2 = "A matrícula é no SIGAA";
    private static final Boolean DONE_TASK2 = false;
    private static final LocalDateTime FINALIZATION_DATE_TASK2 = LocalDateTime.of(LocalDateTime.now().getYear(), 12, 12, 0, 0);

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TeamRepository teamRepository;

    private TaskEntity taskEntity;

    @BeforeEach
    void setup() {
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName("Nome do time_teste");
        teamRepository.save(teamEntity);

        taskEntity = new TaskEntity();
        taskEntity.setTitle(TITLE_TASK1);
        taskEntity.setDescription(DESCRIPTION_TASK1);
        taskEntity.setDone(DONE_TASK1);
        taskEntity.setFinalizationDate(FINALIZATION_DATE_TASK1);
        taskEntity.setTeam(teamEntity);
    }
}

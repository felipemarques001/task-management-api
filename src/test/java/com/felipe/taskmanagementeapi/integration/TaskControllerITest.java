package com.felipe.taskmanagementeapi.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felipe.taskmanagementeapi.Repositories.EmployeeRepository;
import com.felipe.taskmanagementeapi.Repositories.TaskRepository;
import com.felipe.taskmanagementeapi.Repositories.TeamRepository;
import com.felipe.taskmanagementeapi.dtos.TaskDto;
import com.felipe.taskmanagementeapi.entities.TaskEntity;
import com.felipe.taskmanagementeapi.entities.TeamEntity;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TaskControllerITest {

    private static final String TEAM_NAME = "teamNameTest";
    private static final LocalDateTime TASK_FINALIZATION_DATE = LocalDateTime.of(LocalDateTime.now().getYear(), 12, 31, 0, 0, 0);
    private static final String TASK_TITLE = "titleTest";
    private static final String TASK_DESCRIPTION = "descriptionTest";
    private static final Boolean TASK_DONE = false;
    private static final String TASK_NEW_TITLE = "newTitleTest";
    private static final String TASK_NEW_DESCRIPTION = "newDescriptionTest";
    private static final LocalDateTime TASK_NEW_FINALIZATION_DATE = LocalDateTime.of(LocalDateTime.now().getYear() + 1, 12, 31, 0, 0, 0);

    private static final Integer INVALID_ID = 1;
    private static final String TEAM_NOT_FOUND_ERROR_MESSAGE = "Team not found with id : " + INVALID_ID;
    private static final String TASK_NOT_FOUND_ERROR_MESSAGE = "Task not found with id : " + INVALID_ID;
    private static final String ERROR_DETAILS_URL = "uri=/task";
    private static final String FINALIZATION_DATE_ERROR_MESSAGE = "Finalization date cannot be empty!";
    private static final String TEAM_ID_ERROR_MESSAGE = "Inform the id of the team in which this task was joined";
    private static final String DESCRIPTION_ERROR_MESSAGE = "Description cannot be empty!";
    private static final String TITLE_ERROR_MESSAGE = "Title cannot be empty!";

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
        taskRepository.deleteAll();
        teamRepository.deleteAll();
    }

    @DisplayName("Integration test for createTeam method - success case")
    @Test
    void givenTaskDto_whenCreateTask_thenReturnSavedTask() throws Exception {
        // given
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(TEAM_NAME);
        teamRepository.save(teamEntity);

        TaskDto taskDto = new TaskDto();
        taskDto.setFinalizationDate(TASK_FINALIZATION_DATE);
        taskDto.setTitle(TASK_TITLE);
        taskDto.setDescription(TASK_DESCRIPTION);
        taskDto.setTeamId(teamEntity.getId());

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/task")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskDto)));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.title", CoreMatchers.is(TASK_TITLE)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.description", CoreMatchers.is(TASK_DESCRIPTION)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.teamId", CoreMatchers.is(teamEntity.getId())))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.done", CoreMatchers.is(TASK_DONE)));
    }

    @DisplayName("Integration test for createTeam method - fail case - invalid team id")
    @Test
    void givenInvalidTeamId_whenCreateTask_thenResourceNotFoundAndErrorDetails() throws Exception {
        // given
        TaskDto taskDto = new TaskDto();
        taskDto.setFinalizationDate(TASK_FINALIZATION_DATE);
        taskDto.setTitle(TASK_TITLE);
        taskDto.setDescription(TASK_DESCRIPTION);
        taskDto.setTeamId(INVALID_ID);

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/task")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskDto)));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", CoreMatchers.is(TEAM_NOT_FOUND_ERROR_MESSAGE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.details", CoreMatchers.is(ERROR_DETAILS_URL)));
    }

    @DisplayName("Integration test for createTask method - fail case - invalid taskDto")
    @Test
    void givenInvalidTaskDto_whenCreateTask_thenBadRequestAndErrorDetails() throws Exception {
        // given
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(TEAM_NAME);
        teamRepository.save(teamEntity);

        TaskDto taskDto = new TaskDto();

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/task")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskDto)));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.finalizationDate", CoreMatchers.is(FINALIZATION_DATE_ERROR_MESSAGE)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.teamId", CoreMatchers.is(TEAM_ID_ERROR_MESSAGE)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.description", CoreMatchers.is(DESCRIPTION_ERROR_MESSAGE)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.title", CoreMatchers.is(TITLE_ERROR_MESSAGE)));
    }

    @DisplayName("Integration test for findTaskById method - success case")
    @Test
    void givenValidId_whenFindTaskById_thenReturnSavedTask() throws Exception {
        // given
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(TEAM_NAME);
        teamRepository.save(teamEntity);

        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(TASK_TITLE);
        taskEntity.setDescription(TASK_DESCRIPTION);
        taskEntity.setFinalizationDate(TASK_FINALIZATION_DATE);
        taskEntity.setDone(TASK_DONE);
        taskEntity.setTeam(teamEntity);
        taskRepository.save(taskEntity);

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .get("/task/{id}", taskEntity.getId()));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.title", CoreMatchers.is(TASK_TITLE)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.description", CoreMatchers.is(TASK_DESCRIPTION)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.teamId", CoreMatchers.is(teamEntity.getId())))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.done", CoreMatchers.is(TASK_DONE)));
    }

    @DisplayName("Integration test for findTaskById method - fail case")
    @Test
    void givenInvalidId_whenFindTaskById_thenResourceNotFoundAndErrorDetails() throws Exception {
        // given
        // nothing

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .get("/task/{id}", INVALID_ID));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", CoreMatchers.is(TASK_NOT_FOUND_ERROR_MESSAGE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.details", CoreMatchers.is(ERROR_DETAILS_URL + "/" + INVALID_ID)));
    }

    @DisplayName("Integration test for findAllTasks method")
    @Test
    void givenSavedTask_whenFindAllTasks_thenReturnTaskDtoList() throws Exception {
        // given
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(TEAM_NAME);
        teamRepository.save(teamEntity);

        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(TASK_TITLE);
        taskEntity.setDescription(TASK_DESCRIPTION);
        taskEntity.setFinalizationDate(TASK_FINALIZATION_DATE);
        taskEntity.setDone(TASK_DONE);
        taskEntity.setTeam(teamEntity);
        taskRepository.save(taskEntity);

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .get("/task"));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()",
                        CoreMatchers.is(1)));
    }

    @DisplayName("Integration test for updateTask method - success case")
    @Test
    void givenNewTaskDto_whenUpdateTask_thenReturnUpdatedTask() throws Exception {
        // given
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(TEAM_NAME);
        teamRepository.save(teamEntity);

        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(TASK_TITLE);
        taskEntity.setDescription(TASK_DESCRIPTION);
        taskEntity.setFinalizationDate(TASK_FINALIZATION_DATE);
        taskEntity.setDone(TASK_DONE);
        taskEntity.setTeam(teamEntity);
        taskRepository.save(taskEntity);

        TaskDto newTaskDto = new TaskDto();
        newTaskDto.setFinalizationDate(TASK_NEW_FINALIZATION_DATE);
        newTaskDto.setTitle(TASK_NEW_TITLE);
        newTaskDto.setDescription(TASK_NEW_DESCRIPTION);
        newTaskDto.setTeamId(teamEntity.getId());

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .put("/task/{id}", taskEntity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newTaskDto)));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.id", CoreMatchers.is(taskEntity.getId())))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.title", CoreMatchers.is(TASK_NEW_TITLE)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.description", CoreMatchers.is(TASK_NEW_DESCRIPTION)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.teamId", CoreMatchers.is(teamEntity.getId())))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.done", CoreMatchers.is(TASK_DONE)));
    }

    @DisplayName("Integration test for updateTask method - fail case - Invalid Id")
    @Test
    void givenInvalidId_whenUpdateTask_thenResourceNotFoundAndErrorDetails() throws Exception {
        // given
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(TEAM_NAME);
        teamRepository.save(teamEntity);

        TaskDto newTaskDto = new TaskDto();
        newTaskDto.setFinalizationDate(TASK_NEW_FINALIZATION_DATE);
        newTaskDto.setTitle(TASK_NEW_TITLE);
        newTaskDto.setDescription(TASK_NEW_DESCRIPTION);
        newTaskDto.setTeamId(teamEntity.getId());

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .put("/task/{id}", INVALID_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newTaskDto)));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", CoreMatchers.is(TASK_NOT_FOUND_ERROR_MESSAGE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.details", CoreMatchers.is(ERROR_DETAILS_URL + "/" + INVALID_ID)));
    }

    @DisplayName("Integration test for updateTask method - fail case - invalid taskDto")
    @Test
    void givenInvalidTaskDto_whenUpdateTask_thenReturnBadRequestAndErrorDetails() throws Exception {
        // given
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(TEAM_NAME);
        teamRepository.save(teamEntity);

        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(TASK_TITLE);
        taskEntity.setDescription(TASK_DESCRIPTION);
        taskEntity.setFinalizationDate(TASK_FINALIZATION_DATE);
        taskEntity.setDone(TASK_DONE);
        taskEntity.setTeam(teamEntity);
        taskRepository.save(taskEntity);

        TaskDto newTaskDto = new TaskDto();

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .put("/task/{id}", taskEntity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newTaskDto)));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.finalizationDate", CoreMatchers.is(FINALIZATION_DATE_ERROR_MESSAGE)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.teamId", CoreMatchers.is(TEAM_ID_ERROR_MESSAGE)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.description", CoreMatchers.is(DESCRIPTION_ERROR_MESSAGE)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.title", CoreMatchers.is(TITLE_ERROR_MESSAGE)));
    }

    @DisplayName("Integration test for deleteTaskById method - success case")
    @Test
    void givenValidId_whenDeleteTaskById_thenReturnOkAndSuccessString() throws Exception {
        // given
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(TEAM_NAME);
        teamRepository.save(teamEntity);

        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(TASK_TITLE);
        taskEntity.setDescription(TASK_DESCRIPTION);
        taskEntity.setFinalizationDate(TASK_FINALIZATION_DATE);
        taskEntity.setDone(TASK_DONE);
        taskEntity.setTeam(teamEntity);
        taskRepository.save(taskEntity);

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .delete("/task/{id}", taskEntity.getId()));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("Integration test for deleteTaskById method - fail case")
    @Test
    void givenInvalidId_whenDeleteTaskById_thenReturnResourceNotFoundAndErrorDetails() throws Exception {
        // given
        // nothing

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .delete("/task/{id}", 1));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", CoreMatchers.is(TASK_NOT_FOUND_ERROR_MESSAGE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.details", CoreMatchers.is(ERROR_DETAILS_URL + "/" + INVALID_ID)));
    }
}

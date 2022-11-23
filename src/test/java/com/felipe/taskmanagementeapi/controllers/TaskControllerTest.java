package com.felipe.taskmanagementeapi.controllers;

import com.felipe.taskmanagementeapi.dtos.TaskDto;
import com.felipe.taskmanagementeapi.services.impl.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskControllerTest {

    private static final Integer ID_TASK = 1;
    private static final String TITLE_TASK = "Nome da terefa teste 1";
    private static final String DESCRIPTION_TASK = "Descrição da tarefa teste 1";
    private static final Boolean DONE_TASK = false;
    private static final LocalDateTime CREATION_DATE_TASK = LocalDateTime.now();
    private static final LocalDateTime FINALIZATION_DATE_TASK = LocalDateTime.of(LocalDateTime.now().getYear(),
            12, 31, 0, 0);
    private static final Integer ID_TEAM = 1;

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskServiceImpl taskService;

    private TaskDto savedTask;
    private TaskDto requestTaskDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        savedTask = new TaskDto();
        requestTaskDto = new TaskDto();

        savedTask.setId(ID_TASK);
        savedTask.setCreationDate(CREATION_DATE_TASK);
        savedTask.setFinalizationDate(FINALIZATION_DATE_TASK);
        savedTask.setTitle(TITLE_TASK);
        savedTask.setDescription(DESCRIPTION_TASK);
        savedTask.setDone(DONE_TASK);

        requestTaskDto.setFinalizationDate(FINALIZATION_DATE_TASK);
        requestTaskDto.setTitle(TITLE_TASK);
        requestTaskDto.setDescription(DESCRIPTION_TASK);
        requestTaskDto.setTeamId(ID_TEAM);
    }

    @DisplayName("Unit test for creteTask method")
    @Test
    void givenRequestTaskDto_whenCreateTask_thenReturnSavedTask() {
        // given
        Mockito.when(taskService.createTask(Mockito.any(TaskDto.class))).thenReturn(savedTask);

        // when
        ResponseEntity<TaskDto> response = taskController.createTask(requestTaskDto);

        // then
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(TaskDto.class, response.getBody().getClass());
        assertEquals(ID_TASK, response.getBody().getId());
        assertEquals(CREATION_DATE_TASK, response.getBody().getCreationDate());
        assertEquals(FINALIZATION_DATE_TASK, response.getBody().getFinalizationDate());
        assertEquals(TITLE_TASK, response.getBody().getTitle());
        assertEquals(DESCRIPTION_TASK, response.getBody().getDescription());
        assertEquals(DONE_TASK, response.getBody().getDone());
    }

    @DisplayName("Unit test for findTaskById method")
    @Test
    void givenValidId_whenFindTaskById_thenReturnSavedTask() {
        // given
        Mockito.when(taskService.findTaskById(Mockito.anyInt())).thenReturn(savedTask);

        // when
        ResponseEntity<TaskDto> response = taskController.findTaskById(ID_TASK);

        // then
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(TaskDto.class, response.getBody().getClass());
        assertEquals(ID_TASK, response.getBody().getId());
        assertEquals(CREATION_DATE_TASK, response.getBody().getCreationDate());
        assertEquals(FINALIZATION_DATE_TASK, response.getBody().getFinalizationDate());
        assertEquals(TITLE_TASK, response.getBody().getTitle());
        assertEquals(DESCRIPTION_TASK, response.getBody().getDescription());
        assertEquals(DONE_TASK, response.getBody().getDone());
    }

    @DisplayName("Unit test for findAllTasks method")
    @Test
    void givenSavedTaskList_whenFindAllTasks_thenReturnListTaskDto() {
        // given
        Mockito.when(taskService.findAllTasks()).thenReturn(List.of(savedTask));

        // when
        ResponseEntity<List<TaskDto>> response = taskController.findAllTasks();

        // then
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().get(0));
        assertEquals(TaskDto.class, response.getBody().get(0).getClass());
        assertEquals(ID_TASK, response.getBody().get(0).getId());
        assertEquals(CREATION_DATE_TASK, response.getBody().get(0).getCreationDate());
        assertEquals(FINALIZATION_DATE_TASK, response.getBody().get(0).getFinalizationDate());
        assertEquals(TITLE_TASK, response.getBody().get(0).getTitle());
        assertEquals(DESCRIPTION_TASK, response.getBody().get(0).getDescription());
        assertEquals(DONE_TASK, response.getBody().get(0).getDone());
    }

    @DisplayName("Unit test for updateTask method")
    @Test
    void givenUpdatedTaskDto_whenUpdateTask_thenReturnNewTaskDto() {
        // given
        LocalDateTime newFinalizationDate = LocalDateTime.of(LocalDateTime.now().getYear() + 1,
                12, 31, 0, 0);
        String newTitle = "newTitle";
        String newDescription = "newDescription";
        Boolean newDone = true;

        savedTask.setFinalizationDate(newFinalizationDate);
        savedTask.setTitle(newTitle);
        savedTask.setDescription(newDescription);
        savedTask.setDone(newDone);

        requestTaskDto.setFinalizationDate(newFinalizationDate);
        requestTaskDto.setTitle(newTitle);
        requestTaskDto.setDescription(newDescription);
        requestTaskDto.setDone(newDone);

        Mockito.when(taskService.updateTask(Mockito.any(TaskDto.class), Mockito.anyInt())).thenReturn(savedTask);

        // when
        ResponseEntity<TaskDto> response = taskController.updateTask(requestTaskDto, ID_TASK);

        // then
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(TaskDto.class, response.getBody().getClass());
        assertEquals(ID_TASK, response.getBody().getId());
        assertEquals(CREATION_DATE_TASK, response.getBody().getCreationDate());
        assertEquals(newFinalizationDate, response.getBody().getFinalizationDate());
        assertEquals(newTitle, response.getBody().getTitle());
        assertEquals(newDescription, response.getBody().getDescription());
        assertEquals(newDone, response.getBody().getDone());
    }

    @DisplayName("Unit test for deleteTaskById method")
    @Test
    void givenValidId_whenDeleteTaskById_thenReturnSuccessString() {
        // given
        String successString = "Task successfully deleted!";
        Mockito.when(taskService.deleteTaskById(Mockito.anyInt())).thenReturn(successString);

        // when
        ResponseEntity<String> response = taskController.deleteTaskById(ID_TASK);

        // then
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(String.class, response.getBody().getClass());
        assertEquals(successString, response.getBody());
        Mockito.verify(taskService, Mockito.times(1)).deleteTaskById(Mockito.anyInt());
    }
}
package com.felipe.taskmanagementeapi.services;

import com.felipe.taskmanagementeapi.Repositories.TaskRepository;
import com.felipe.taskmanagementeapi.Repositories.TeamRepository;
import com.felipe.taskmanagementeapi.dtos.TaskDto;
import com.felipe.taskmanagementeapi.entities.TaskEntity;
import com.felipe.taskmanagementeapi.entities.TeamEntity;
import com.felipe.taskmanagementeapi.exceptions.ResourceNotFoundException;
import com.felipe.taskmanagementeapi.services.impl.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TaskServiceImplTest {

    private static final Integer ID_TASK = 1;
    private static final String TITLE_TASK = "Nome da terefa teste 1";
    private static final String DESCRIPTION_TASK = "Descrição da tarefa teste 1";
    private static final Boolean DONE_TASK = false;
    private static final LocalDateTime CREATION_DATE_TASK = LocalDateTime.now();
    private static final LocalDateTime FINALIZATION_DATE_TASK = LocalDateTime.of(LocalDateTime.now().getYear(), 12, 31, 0, 0);

    private static final String NAME_TEAM = "Departamento de TI";
    private static final Integer ID_TEAM = 1;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TeamRepository teamRepository;

    private TaskEntity savedTask;
    private TaskDto taskDto;
    private TeamEntity savedTeam;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        savedTeam = new TeamEntity();
        savedTeam.setId(ID_TEAM);
        savedTeam.setName(NAME_TEAM);

        savedTask = new TaskEntity();
        savedTask.setId(ID_TASK);
        savedTask.setTitle(TITLE_TASK);
        savedTask.setDescription(DESCRIPTION_TASK);
        savedTask.setCreationDate(CREATION_DATE_TASK);
        savedTask.setFinalizationDate(FINALIZATION_DATE_TASK);
        savedTask.setDone(DONE_TASK);
        savedTask.setTeam(savedTeam);

        taskDto = new TaskDto();
        taskDto.setFinalizationDate(FINALIZATION_DATE_TASK);
        taskDto.setTitle(TITLE_TASK);
        taskDto.setDescription(DESCRIPTION_TASK);
        taskDto.setTeamId(ID_TEAM);
    }

    @DisplayName("Unit test for createTask method")
    @Test
    void givenTaskDto_whenCreateTask_thenReturnSavedTask() {
        // given
        Mockito.when(teamRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(savedTeam));
        // As this save return is not being stored in the createTask method, it is okay to let Mockito return the same savedTask field.
        Mockito.when(taskRepository.save(Mockito.any(TaskEntity.class))).thenReturn(savedTask);

        // when
        TaskDto response = taskService.createTask(taskDto);

        // then
        assertEquals(ID_TASK, response.getId());
        assertEquals(TITLE_TASK, response.getTitle());
        assertEquals(DESCRIPTION_TASK, response.getDescription());
        assertEquals(CREATION_DATE_TASK, response.getCreationDate());
        assertEquals(FINALIZATION_DATE_TASK, response.getFinalizationDate());
        assertEquals(DONE_TASK, response.getDone());
        assertEquals(ID_TEAM, response.getTeamId());
    }

    @DisplayName("Unit test for findTaskById method - success case")
    @Test
    void givenValidId_whenFindTaskById_thenReturnSavedTask() {
        // given
        Mockito.when(taskRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(savedTask));

        // when
        TaskDto response = taskService.findTaskById(ID_TASK);

        // then
        assertEquals(ID_TASK, response.getId());
        assertEquals(TITLE_TASK, response.getTitle());
        assertEquals(DESCRIPTION_TASK, response.getDescription());
        assertEquals(CREATION_DATE_TASK, response.getCreationDate());
        assertEquals(FINALIZATION_DATE_TASK, response.getFinalizationDate());
        assertEquals(DONE_TASK, response.getDone());
    }

    @DisplayName("Unit test for findTaskById method - fail case")
    @Test
    void givenInvalidId_whenFindTaskById_thenThrowsResourceNotFoundException() {
        // given
        Mockito.when(taskRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        // when
        try {
            TaskDto response = taskService.findTaskById(ID_TASK);
        } catch (Exception ex) {
            // then
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("Task not found with id : 1", ex.getMessage());
        }
    }

    @DisplayName("Unit test for findAllTasks method")
    @Test
    void givenTaskEntityList_whenFindAllTasks_thenReturnTaskDtoList() {
        // given
        Mockito.when(taskRepository.findAll()).thenReturn(List.of(savedTask));

        // when
        List<TaskDto> taskDtoList = taskService.findAllTasks();

        // then
        assertEquals(ID_TASK, taskDtoList.get(0).getId());
        assertEquals(TITLE_TASK, taskDtoList.get(0).getTitle());
        assertEquals(DESCRIPTION_TASK, taskDtoList.get(0).getDescription());
        assertEquals(CREATION_DATE_TASK, taskDtoList.get(0).getCreationDate());
        assertEquals(FINALIZATION_DATE_TASK, taskDtoList.get(0).getFinalizationDate());
        assertEquals(DONE_TASK, taskDtoList.get(0).getDone());
    }

    @DisplayName("Unit test for updateTask method - case success")
    @Test
    void givenNewTaskDto_whenUpdateTask_thenReturnUpdatedTask() {
        // given
        String newTaskName= "Nome tarefa teste 2";
        String newTaskDescription = "Nome tarefa teste 2";
        LocalDateTime newTaskFinalizationDate = LocalDateTime.of(LocalDateTime.now().getYear() + 1, 12, 31, 0, 0);
        Boolean newTaskDone = true;

        TaskDto updatedTaskDto = new TaskDto();
        updatedTaskDto.setFinalizationDate(newTaskFinalizationDate);
        updatedTaskDto.setTitle(newTaskName);
        updatedTaskDto.setDescription(newTaskDescription);
        updatedTaskDto.setDone(newTaskDone);

        Mockito.when(taskRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(savedTask));
        Mockito.when(taskRepository.save(Mockito.any(TaskEntity.class))).thenReturn(savedTask);

        // when
        TaskDto response = taskService.updateTask(updatedTaskDto, ID_TASK);

        // then
        assertEquals(ID_TASK, response.getId());
        assertEquals(newTaskName, response.getTitle());
        assertEquals(newTaskDescription, response.getDescription());
        assertEquals(CREATION_DATE_TASK, response.getCreationDate());
        assertEquals(newTaskFinalizationDate, response.getFinalizationDate());
        assertEquals(newTaskDone, response.getDone());
    }

    @DisplayName("Unit test for updateTask method - fail case")
    @Test
    void givenInvalidId_whenUpdateTask_thenThrowsResourceNotFoundException() {
        // given
        Mockito.when(taskRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        // I will let updatedTaskDto without values in its attributes, because I won't use them
        TaskDto updatedTaskDto = new TaskDto();

        // when
        try {
            TaskDto response = taskService.updateTask(updatedTaskDto, ID_TASK);
        } catch (Exception ex) {
            // then
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("Task not found with id : 1", ex.getMessage());
        }
    }

    @DisplayName("Unit test for deleteTaskById - success case")
    @Test
    void whenValidId_whenDeleteTaskById_thenDeleteWithSuccess() {
        // given
        Mockito.when(taskRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(savedTask));
        Mockito.doNothing().when(taskRepository).deleteById(Mockito.anyInt());

        // when
        String response = taskService.deleteTaskById(ID_TASK);

        // then
        assertNotNull(response);
        assertEquals("Task successfully deleted!", response);
        Mockito.verify(taskRepository, Mockito.times(1)).deleteById(Mockito.anyInt());
    }

    @DisplayName("Unit test for deleteTaskById - fail case")
    @Test
    void whenInvalidId_whenDeleteTaskById_thenThrowsResourceNotFoundException() {
        // given
        Mockito.when(taskRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        // when
        try {
            String response = taskService.deleteTaskById(ID_TASK);
        } catch (Exception ex) {
            // then
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("Task not found with id : 1", ex.getMessage());
        }
    }
}

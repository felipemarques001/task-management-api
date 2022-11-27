package com.felipe.taskmanagementeapi.controllers;

import com.felipe.taskmanagementeapi.dtos.TaskDto;
import com.felipe.taskmanagementeapi.services.impl.TaskServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "CRUD Rest APIs for Task entity")
@RestController
@RequestMapping(value = "/task")
public class TaskController {

    @Autowired
    private TaskServiceImpl taskService;

    @ApiOperation(value = "Add Task to the Team")
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody @Valid TaskDto taskDto) {
        TaskDto savedTaskDto = taskService.createTask(taskDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTaskDto);
    }

    @ApiOperation(value = "Find Task BY Id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskDto> findTaskById(@PathVariable Integer id) {
        TaskDto savedTaskDto = taskService.findTaskById(id);
        return ResponseEntity.status(HttpStatus.OK).body(savedTaskDto);
    }

    @ApiOperation(value = "Find all Tasks")
    @GetMapping
    public ResponseEntity<List<TaskDto>> findAllTasks() {
        List<TaskDto> savedTaskDtoList = taskService.findAllTasks();
        return ResponseEntity.status(HttpStatus.OK).body(savedTaskDtoList);
    }

    @ApiOperation(value = "Update Task")
    @PutMapping(value = "/{id}")
    public ResponseEntity<TaskDto> updateTask(@RequestBody @Valid TaskDto taskDto, @PathVariable Integer id) {
        TaskDto updatedTaskDto = taskService.updateTask(taskDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTaskDto);
    }

    @ApiOperation(value = "Delete Task BY Id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable Integer id) {
        String responseService = taskService.deleteTaskById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseService);
    }
}

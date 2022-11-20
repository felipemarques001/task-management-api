package com.felipe.taskmanagementeapi.controllers;

import com.felipe.taskmanagementeapi.dtos.TaskDto;
import com.felipe.taskmanagementeapi.services.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/task")
public class TaskController {

    @Autowired
    private TaskServiceImpl taskService;

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        TaskDto savedTaskDto = taskService.createTask(taskDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskDto> findTaskById(@PathVariable Integer id) {
        TaskDto savedTaskDto = taskService.findTaskById(id);
        return ResponseEntity.status(HttpStatus.OK).body(savedTaskDto);
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> findAllTasks() {
        List<TaskDto> savedTaskDtoList = taskService.findAllTasks();
        return ResponseEntity.status(HttpStatus.OK).body(savedTaskDtoList);
    }
}

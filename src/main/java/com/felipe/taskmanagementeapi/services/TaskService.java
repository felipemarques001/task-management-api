package com.felipe.taskmanagementeapi.services;

import com.felipe.taskmanagementeapi.dtos.TaskDto;

import java.util.List;

public interface TaskService {

    TaskDto createTask(TaskDto taskDto);

    TaskDto findTaskById(Integer id);

    List<TaskDto> findAllTasks();

    TaskDto updateTask(TaskDto taskDto, Integer id);
}

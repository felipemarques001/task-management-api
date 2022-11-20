package com.felipe.taskmanagementeapi.services;

import com.felipe.taskmanagementeapi.dtos.TaskDto;

public interface TaskService {
    TaskDto createTask(TaskDto taskDto);
}

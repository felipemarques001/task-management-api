package com.felipe.taskmanagementeapi.services.impl;

import com.felipe.taskmanagementeapi.Repositories.TaskRepository;
import com.felipe.taskmanagementeapi.Repositories.TeamRepository;
import com.felipe.taskmanagementeapi.dtos.TaskDto;
import com.felipe.taskmanagementeapi.entities.TaskEntity;
import com.felipe.taskmanagementeapi.entities.TeamEntity;
import com.felipe.taskmanagementeapi.services.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        Optional<TeamEntity> optionalTeam = teamRepository.findById(taskDto.getTeamId());
        TaskEntity taskEntity = new TaskEntity();
        BeanUtils.copyProperties(taskDto, taskEntity);

        taskEntity.setTeam(optionalTeam.get());
        taskEntity.setDone(false);

        taskRepository.save(taskEntity);
        BeanUtils.copyProperties(taskEntity, taskDto);
        return taskDto;
    }

    @Override
    public TaskDto findTaskById(Integer id) {
        Optional<TaskEntity> savedTask = taskRepository.findById(id);
        TaskDto taskDto = new TaskDto();
        BeanUtils.copyProperties(savedTask.get(), taskDto);
        return taskDto;
    }

    @Override
    public List<TaskDto> findAllTasks() {
        List<TaskEntity> taskEntityList = taskRepository.findAll();
        List<TaskDto> taskDtoList = taskEntityList.stream().map(TaskDto::new).toList();
        return taskDtoList;
    }


}

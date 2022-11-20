package com.felipe.taskmanagementeapi.services.impl;

import com.felipe.taskmanagementeapi.Repositories.TaskRepository;
import com.felipe.taskmanagementeapi.Repositories.TeamRepository;
import com.felipe.taskmanagementeapi.dtos.TaskDto;
import com.felipe.taskmanagementeapi.entities.TaskEntity;
import com.felipe.taskmanagementeapi.entities.TeamEntity;
import com.felipe.taskmanagementeapi.exceptions.ResourceNotFoundException;
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
        if(optionalTeam.isEmpty()) {
            throw new ResourceNotFoundException("Team", "id", taskDto.getTeamId());
        }

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
        Optional<TaskEntity> savedTaskOptional = taskRepository.findById(id);
        if(savedTaskOptional.isEmpty()) {
            throw new ResourceNotFoundException("Task", "id", id);
        }
        TaskDto taskDto = new TaskDto();
        BeanUtils.copyProperties(savedTaskOptional.get(), taskDto);
        return taskDto;
    }

    @Override
    public List<TaskDto> findAllTasks() {
        List<TaskEntity> taskEntityList = taskRepository.findAll();
        List<TaskDto> taskDtoList = taskEntityList.stream().map(TaskDto::new).toList();
        return taskDtoList;
    }

    @Override
    public TaskDto updateTask(TaskDto taskDto, Integer id) {
        Optional<TaskEntity> savedTaskOptional = taskRepository.findById(id);
        if(savedTaskOptional.isEmpty()) {
            throw new ResourceNotFoundException("Task", "id", id);
        }
        TaskEntity savedTask = savedTaskOptional.get();
        savedTask.setTitle(taskDto.getTitle());
        savedTask.setDescription(taskDto.getDescription());
        savedTask.setFinalizationDate(taskDto.getFinalizationDate());
        if(taskDto.getDone() == null) {
            savedTask.setDone(false);
        } else {
            savedTask.setDone(taskDto.getDone());
        }
        taskRepository.save(savedTask);

        BeanUtils.copyProperties(savedTask, taskDto);
        return taskDto;
    }

    @Override
    public String deleteTaskById(Integer id) {
        Optional<TaskEntity> savedTaskOptional = taskRepository.findById(id);
        if(savedTaskOptional.isEmpty()) {
            throw new ResourceNotFoundException("Task", "id", id);
        }
        taskRepository.deleteById(id);
        return "Task successfully deleted!";
    }
}

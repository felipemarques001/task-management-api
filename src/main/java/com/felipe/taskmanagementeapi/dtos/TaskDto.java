package com.felipe.taskmanagementeapi.dtos;

import com.felipe.taskmanagementeapi.entities.TaskEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@ApiModel(description = "Task model information")
@Getter
@Setter
@NoArgsConstructor
public class TaskDto {

    @ApiModelProperty(value = "Task ID")
    private Integer id;

    @ApiModelProperty(value = "Task creation date")
    private LocalDateTime creationDate;

    @ApiModelProperty(value = "Task finalization date")
    @NotNull(message = "Finalization date cannot be empty!")
    private LocalDateTime finalizationDate;

    @ApiModelProperty(value = "Task title")
    @NotBlank(message = "Title cannot be empty!")
    private String title;

    @ApiModelProperty(value = "Task description")
    @NotBlank(message = "Description cannot be empty!")
    private String description;

    @ApiModelProperty(value = "Task team ID")
    @NotNull(message = "Inform the id of the team in which this task was joined")
    private Integer teamId;

    @ApiModelProperty(value = "Task done")
    private Boolean done;

    public TaskDto(TaskEntity taskEntity) {
        id = taskEntity.getId();
        creationDate = taskEntity.getCreationDate();
        finalizationDate = taskEntity.getFinalizationDate();
        title = taskEntity.getTitle();
        description = taskEntity.getDescription();
        done = taskEntity.getDone();
        teamId = taskEntity.getTeam().getId();
    }
}

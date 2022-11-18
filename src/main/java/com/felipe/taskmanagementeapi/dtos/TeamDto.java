package com.felipe.taskmanagementeapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.felipe.taskmanagementeapi.entities.EmployeeEntity;
import com.felipe.taskmanagementeapi.entities.TaskEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class TeamDto {

    private Integer id;

    @NotBlank(message = "Name cannot be empty!")
    private String name;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<TaskEntity> tasks;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<EmployeeEntity> employees;
}

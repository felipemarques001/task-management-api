package com.felipe.taskmanagementeapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.felipe.taskmanagementeapi.entities.EmployeeEntity;
import com.felipe.taskmanagementeapi.entities.TaskEntity;
import com.felipe.taskmanagementeapi.entities.TeamEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@ApiModel(description = "Team model information")
@Getter
@Setter
@NoArgsConstructor
public class TeamDto {

    @ApiModelProperty(value = "Team ID")
    private Integer id;

    @ApiModelProperty(value = "Team name")
    @NotBlank(message = "Name cannot be empty!")
    private String name;

    @ApiModelProperty(value = "Team Task list")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<TaskEntity> tasks = new HashSet<>();

    @ApiModelProperty(value = "Team Employee list")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<EmployeeEntity> employees = new HashSet<>();

    public TeamDto(TeamEntity teamEntity) {
        id = teamEntity.getId();
        name = teamEntity.getName();
        tasks = teamEntity.getTasks();
        employees = teamEntity.getEmployees();
    }
}

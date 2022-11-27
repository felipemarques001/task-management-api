package com.felipe.taskmanagementeapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.felipe.taskmanagementeapi.entities.EmployeeEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(description = "Employee model information")
@Getter
@Setter
@NoArgsConstructor
public class EmployeeDto {

    @ApiModelProperty(value = "Employee ID")
    private Integer id;

    @ApiModelProperty(value = "Employee first name")
    @NotBlank(message = "First name cannot be empty!")
    private String firstName;

    @ApiModelProperty(value = "Employee last name")
    @NotBlank(message = "Last name cannot be empty!")
    private String lastName;

    @ApiModelProperty(value = "Employee role")
    @NotBlank(message = "Role cannot be empty!")
    private String role;

    @ApiModelProperty(value = "Employee team ID")
    @NotNull(message = "Inform the team id in which this employee is working!")
    private Integer teamId;

    public EmployeeDto(EmployeeEntity employeeEntity) {
        id = employeeEntity.getId();
        firstName = employeeEntity.getFirstName();
        lastName = employeeEntity.getLastName();
        role = employeeEntity.getRole();
        teamId = employeeEntity.getTeam().getId();
    }
}

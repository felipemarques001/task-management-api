package com.felipe.taskmanagementeapi.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDto {

    @NotBlank(message = "First name cannot be empty!")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty!")
    private String lastName;

    @NotBlank(message = "Role cannot be empty!")
    private String role;

    @NotNull(message = "Inform the team id in which this employee is working")
    private Integer team_id;

    // Field that cannot be filled by GET requests
    private Integer id;
}

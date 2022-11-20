package com.felipe.taskmanagementeapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDto {

    private Integer id;

    @NotBlank(message = "First name cannot be empty!")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty!")
    private String lastName;

    @NotBlank(message = "Role cannot be empty!")
    private String role;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @NotNull(message = "Inform the team id in which this employee is working")
    private Integer teamId;
}

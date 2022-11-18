package com.felipe.taskmanagementeapi.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TaskDto {

    @NotNull(message = "Finalization date cannot be empty!")
    private LocalDateTime finalizationDate;

    @NotBlank(message = "Title cannot be empty!")
    private String title;

    @NotBlank(message = "Description cannot be empty!")
    private String description;

    @NotNull(message = "Inform the team id in which this employee is working")
    private Integer teamId;

    // Fields that cannot be filled by GET requests
    private Integer id;

    private LocalDateTime creationDate;

    private Boolean done;
}

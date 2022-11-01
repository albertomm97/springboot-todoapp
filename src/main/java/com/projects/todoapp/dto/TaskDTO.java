package com.projects.todoapp.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDTO {
    private String title;
    private String description;
    private LocalDateTime eta;
}

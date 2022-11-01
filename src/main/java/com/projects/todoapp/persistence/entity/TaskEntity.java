package com.projects.todoapp.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.config.Task;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @NotEmpty
    @Size(min = 4, max = 50)
    private String title;

    @NotBlank
    @NotEmpty
    @Size(min = 4, max = 100)
    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime eta;
    private TaskStatus taskStatus;
}

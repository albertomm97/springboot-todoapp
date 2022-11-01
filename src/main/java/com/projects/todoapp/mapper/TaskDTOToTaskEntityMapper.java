package com.projects.todoapp.mapper;

import com.projects.todoapp.dto.TaskDTO;
import com.projects.todoapp.persistence.entity.TaskEntity;
import com.projects.todoapp.persistence.entity.TaskStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskDTOToTaskEntityMapper {

    public TaskEntity map(TaskDTO in) {
        return TaskEntity.builder()
                .title(in.getTitle())
                .description((in.getDescription()))
                .eta(in.getEta())
                .createdAt(LocalDateTime.now())
                .taskStatus(TaskStatus.ON_TIME)
                .build();
    }
}

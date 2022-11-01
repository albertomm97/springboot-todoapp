package com.projects.todoapp.service;

import com.projects.todoapp.dto.TaskDTO;
import com.projects.todoapp.persistence.entity.TaskEntity;
import com.projects.todoapp.persistence.entity.TaskStatus;

import java.util.List;

public interface TaskService {
    public TaskEntity createTask(TaskDTO task);
    public List<TaskEntity> getAllTasks();
    public List<TaskEntity> getTaskByTaskStatus(TaskStatus status);
    public void finishTask(Long id);
    public void deleteTask(Long id);
}

package com.projects.todoapp.service;

import com.projects.todoapp.dto.TaskDTO;
import com.projects.todoapp.persistence.entity.TaskEntity;

import java.util.List;

public interface TaskService {
    public TaskEntity createTask(TaskDTO task);
    public List<TaskEntity> getAllTasks();
    public List<TaskEntity> getTaskByTaskStatus(String status);
    public void finishTask(Long id);
    public void deleteTask(Long id);
}

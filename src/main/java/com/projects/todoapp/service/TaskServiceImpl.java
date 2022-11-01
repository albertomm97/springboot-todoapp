package com.projects.todoapp.service;

import com.projects.todoapp.dto.TaskDTO;
import com.projects.todoapp.exceptions.TaskNotFoundException;
import com.projects.todoapp.mapper.TaskDTOToTaskEntityMapper;
import com.projects.todoapp.persistence.entity.TaskEntity;
import com.projects.todoapp.persistence.entity.TaskStatus;
import com.projects.todoapp.persistence.repository.TaskRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Primary
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskDTOToTaskEntityMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskDTOToTaskEntityMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Transactional
    @Override
    public TaskEntity createTask(TaskDTO task) {
        return this.taskRepository.save(taskMapper.map(task));
    }

    @Override
    public List<TaskEntity> getAllTasks() {
        return this.taskRepository.findAll();
    }

    @Override
    public List<TaskEntity> getTaskByTaskStatus(TaskStatus status) {
        return this.taskRepository.findAllByTaskStatus(status);
    }

    @Transactional
    @Override
    public void finishTask(Long id) {
        this.taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Invalid ID provided"));
        this.taskRepository.setTaskStatusToFinished(id);
    }

    @Transactional
    @Override
    public void deleteTask(Long id) {
        this.taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Invalid ID provided"));
        this.taskRepository.deleteById(id);
    }
}

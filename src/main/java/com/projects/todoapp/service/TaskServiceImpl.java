package com.projects.todoapp.service;

import com.projects.todoapp.dto.TaskDTO;
import com.projects.todoapp.exceptions.TaskNotFoundException;
import com.projects.todoapp.mapper.TaskDTOToTaskEntityMapper;
import com.projects.todoapp.persistence.entity.TaskEntity;
import com.projects.todoapp.persistence.entity.TaskStatus;
import com.projects.todoapp.persistence.repository.TaskRepository;
import org.apache.commons.logging.Log;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<TaskEntity> getTaskByTaskStatus(String status) {
        List<TaskStatus> allStatus = List.of(TaskStatus.values());


        Optional<TaskStatus> foundStatus = allStatus.stream()
                .filter(taskStatus -> taskStatus.equals(status))
                .findFirst();

        return this.taskRepository.findAllByTaskStatus(
                foundStatus.orElseThrow(() -> new TaskNotFoundException("Invalid Status provided"))
        ).get();
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

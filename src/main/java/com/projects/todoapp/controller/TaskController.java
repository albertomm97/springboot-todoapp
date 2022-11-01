package com.projects.todoapp.controller;

import com.projects.todoapp.dto.TaskDTO;
import com.projects.todoapp.persistence.entity.TaskEntity;
import com.projects.todoapp.persistence.entity.TaskStatus;
import com.projects.todoapp.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public ResponseEntity<List<TaskEntity>> getAllTasks() {
        List<TaskEntity> tasks = taskService.getAllTasks();

        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskEntity>> getTasksByStatus(@PathVariable(name = "status") String status) {
        List<TaskEntity> tasks = taskService.getTaskByTaskStatus(status);

        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @PostMapping()
    public ResponseEntity<TaskEntity> createTask(@RequestBody TaskDTO task) {
        TaskEntity taskEntity = this.taskService.createTask(task);

        return ResponseEntity.status(HttpStatus.CREATED).body(taskEntity);
    }

    @PatchMapping("/finish/{id}")
    public ResponseEntity<Void> finishTask(@PathVariable(name = "id") Long id) {
        this.taskService.finishTask(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable(name = "id") Long id) {
        this.taskService.deleteTask(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

package com.projects.todoapp.persistence.repository;

import com.projects.todoapp.persistence.entity.TaskEntity;
import com.projects.todoapp.persistence.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    public List<TaskEntity> findAllByTaskStatus(TaskStatus status);

    @Modifying
    @Query(value = "UPDATE TASK SET STATUS=1 WHERE id=:id", nativeQuery = true)
    public void setTaskStatusToFinished(@Param("id") Long id);
}

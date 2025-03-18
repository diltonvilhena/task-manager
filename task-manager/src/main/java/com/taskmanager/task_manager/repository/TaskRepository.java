package com.taskmanager.task_manager.repository;

import com.taskmanager.task_manager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long > {
}

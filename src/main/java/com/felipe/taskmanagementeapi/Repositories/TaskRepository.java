package com.felipe.taskmanagementeapi.Repositories;

import com.felipe.taskmanagementeapi.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {

}

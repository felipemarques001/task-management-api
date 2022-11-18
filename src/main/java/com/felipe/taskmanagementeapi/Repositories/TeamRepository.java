package com.felipe.taskmanagementeapi.Repositories;

import com.felipe.taskmanagementeapi.entities.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Integer> {

}

package com.felipe.taskmanagementeapi.services.impl;

import com.felipe.taskmanagementeapi.Repositories.EmployeeRepository;
import com.felipe.taskmanagementeapi.Repositories.TeamRepository;
import com.felipe.taskmanagementeapi.dtos.EmployeeDto;
import com.felipe.taskmanagementeapi.entities.EmployeeEntity;
import com.felipe.taskmanagementeapi.entities.TeamEntity;
import com.felipe.taskmanagementeapi.services.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employeeDto, employeeEntity);

        Optional<TeamEntity> teamEntityOptional = teamRepository.findById(employeeDto.getTeamId());
        employeeEntity.setTeam(teamEntityOptional.get());
        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);

        BeanUtils.copyProperties(savedEmployee, employeeDto);
        return employeeDto;
    }
}

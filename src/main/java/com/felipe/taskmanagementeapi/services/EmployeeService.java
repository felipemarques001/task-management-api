package com.felipe.taskmanagementeapi.services;

import com.felipe.taskmanagementeapi.dtos.EmployeeDto;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
}

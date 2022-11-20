package com.felipe.taskmanagementeapi.services;

import com.felipe.taskmanagementeapi.dtos.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto findEmployeeById(Integer id);

    List<EmployeeDto> findAllEmployees();

    EmployeeDto updateEmployee(EmployeeDto employeeDto, Integer id);

    String deleteEmployeeById(Integer id);
}

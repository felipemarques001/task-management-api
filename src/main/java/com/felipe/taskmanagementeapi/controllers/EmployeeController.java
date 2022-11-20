package com.felipe.taskmanagementeapi.controllers;

import com.felipe.taskmanagementeapi.dtos.EmployeeDto;
import com.felipe.taskmanagementeapi.services.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployeeDto = employeeService.createEmployee(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployeeDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable Integer id) {
        EmployeeDto savedEmployeeDto = employeeService.findEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(savedEmployeeDto);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAllEmployees() {
        List<EmployeeDto> savedEmployeeDtoList = employeeService.findAllEmployees();
        return ResponseEntity.status(HttpStatus.OK).body(savedEmployeeDtoList);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Integer id) {
        EmployeeDto updatedEmployeeDto = employeeService.updateEmployee(employeeDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedEmployeeDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Integer id) {
        String responseService = employeeService.deleteEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseService);
    }
}

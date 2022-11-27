package com.felipe.taskmanagementeapi.controllers;

import com.felipe.taskmanagementeapi.dtos.EmployeeDto;
import com.felipe.taskmanagementeapi.services.impl.EmployeeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "CRUD Rest APIs for Employee entity")
@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @ApiOperation(value = "Add Employee to the Team")
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        EmployeeDto savedEmployeeDto = employeeService.createEmployee(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployeeDto);
    }

    @ApiOperation(value = "Find Employee BY Id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable Integer id) {
        EmployeeDto savedEmployeeDto = employeeService.findEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(savedEmployeeDto);
    }

    @ApiOperation(value = "Find all Employees")
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAllEmployees() {
        List<EmployeeDto> savedEmployeeDtoList = employeeService.findAllEmployees();
        return ResponseEntity.status(HttpStatus.OK).body(savedEmployeeDtoList);
    }

    @ApiOperation(value = "Update Employee")
    @PutMapping(value = "/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody @Valid EmployeeDto employeeDto, @PathVariable Integer id) {
        EmployeeDto updatedEmployeeDto = employeeService.updateEmployee(employeeDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedEmployeeDto);
    }

    @ApiOperation(value = "Delete Employee BY Id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Integer id) {
        String responseService = employeeService.deleteEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseService);
    }
}

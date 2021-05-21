package com.example.demo.controllers;

import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public long create(@RequestBody Employee employee) {
        employeeRepository.create(employee);
        return employee.getId();
    }

    @PutMapping("/{id}")
    public long update(
        @PathVariable(name = "id") long id,
        @RequestBody() Employee employee
    ) {
        employee.setId(id);
        employeeRepository.update(employee);
        return id;
    }

    @DeleteMapping("/{id}")
    public long delete(@PathVariable(name = "id") long id) {
        employeeRepository.deleteById(id);
        return id;
    }
}

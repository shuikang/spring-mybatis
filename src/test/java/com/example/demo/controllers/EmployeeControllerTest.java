package com.example.demo.controllers;

import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {
    @Autowired
    public TestRestTemplate restTemplate;

    @Autowired
    public EmployeeController employeeController;

    @MockBean
    public EmployeeRepository employeeRepository;

    @Test
    public void getEmployeeList() {
        List<Employee> employees = new ArrayList<>() {{
            add(new Employee());
        }};
        when(employeeRepository.findAll()).thenReturn(employees);
        List<Employee> result = employeeController.getAll();
        assertEquals(1, result.size());
    }
    @Test
    public void getEmployeeListWithRest() {
        List<Employee> employees = new ArrayList<>() {{
            add(new Employee());
        }};
        when(employeeRepository.findAll()).thenReturn(employees);
        ResponseEntity<List<Employee>> response = restTemplate.exchange(
            "/employees",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Employee> result = response.getBody();
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void createEmployee() {
        Employee mockEmployee = mock(Employee.class);
        when(employeeRepository.create(mockEmployee)).thenReturn(1);
        when(mockEmployee.getId()).thenReturn(1L);
        long id = employeeController.create(mockEmployee);
        assertEquals(1L, id);
    }
    @Test
    public void createEmployeeWithRest() {
        Employee mockEmployee = new Employee(1L, "", "", "");
        when(employeeRepository.create(mockEmployee)).thenReturn(1);
        ResponseEntity<Long> response = restTemplate.postForEntity(
            "/employees",
            new HttpEntity<>(mockEmployee),
            Long.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1L, response.getBody());
    }

    @Test
    public void updateEmployee() {
        Employee mockEmployee = mock(Employee.class);
        when(employeeRepository.update(mockEmployee)).thenReturn(1);
        long id = employeeController.update(1L, mockEmployee);
        assertEquals(1L, id);
    }
    @Test
    public void updateEmployeeWithRest() {
        Employee mockEmployee = new Employee(1L, "", "", "");
        when(employeeRepository.update(mockEmployee)).thenReturn(1);
        ResponseEntity<Long> response = restTemplate.exchange(
            "/employees/1",
            HttpMethod.PUT,
            new HttpEntity<>(mockEmployee),
            new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody());
    }

    @Test
    public void deleteEmployee() {
        when(employeeRepository.deleteById(1L)).thenReturn(1);
        long id = employeeController.delete(1L);
        assertEquals(1L, id);
    }
    @Test
    public void deleteEmployeeWithRest() {
        when(employeeRepository.deleteById(1L)).thenReturn(1);
        ResponseEntity<Long> response = restTemplate.exchange(
            "/employees/1",
            HttpMethod.DELETE,
            null,
            new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody());
    }
}

package com.example.demo.controllers;

import com.example.demo.models.Task;
import com.example.demo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    public TaskRepository taskRepository;

    @GetMapping
    public List<Task> getAll() {
        return taskRepository.selectAll();
    }
}

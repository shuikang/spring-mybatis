package com.example.demo.models;

public class Task {
    private long id;
    private long employeeId;
    private String name;

    public Task() {}

    public Task(long id, long employeeId, String name) {
        this.id = id;
        this.employeeId = employeeId;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", name='" + name + '\'' +
                '}';
    }
}

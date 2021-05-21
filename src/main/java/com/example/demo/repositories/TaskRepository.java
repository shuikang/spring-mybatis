package com.example.demo.repositories;

import com.example.demo.models.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TaskRepository {
    @Select("SELECT * FROM tasks")
    public List<Task> selectAll();
}

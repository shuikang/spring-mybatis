package com.example.demo.repositories;

import com.example.demo.models.Employee;
import com.example.demo.models.Task;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeRepository {
    @Select("select * from employees")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(
            property = "tasks", column = "id",
            javaType = java.util.List.class, many = @Many(select = "findTaskByEmployeeId")
        )
    })
    public List<Employee> findAll();

    // Same caller mapper
    @Select("SELECT * FROM tasks WHERE employee_id = #{id}")
    public List<Task> findTaskByEmployeeId(@Param("id") long id);

    @Insert(
        "INSERT INTO employees(first_name, last_name, email) " +
        "VALUES (#{firstName}, #{lastName}, #{email})"
    )
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int create(Employee employee);

    @Update(
        "UPDATE employees set first_name=#{firstName}, " +
        "last_name=#{lastName}, email=#{email} where id=#{id}"
    )
    public int update(Employee employee);

    @Delete("DELETE FROM employees WHERE id = #{id}")
    public int deleteById(long id);
}

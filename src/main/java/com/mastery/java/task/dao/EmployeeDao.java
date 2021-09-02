package com.mastery.java.task.dao;

import java.util.List;
import com.mastery.java.task.dto.Employee;

public interface EmployeeDao {

	List<Employee> findAll();

	Employee findById(int id);

	Employee save(Employee employee);

	Employee update(Employee employee);

	int deleteById(int id);

	int maxEmployeeId();

}
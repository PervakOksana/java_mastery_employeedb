package com.mastery.java.task.service;

import java.util.List;

import com.mastery.java.task.dto.Employee;

public interface EmployeeService {

	List<Employee> getAll();

	Employee getById(String employeeId);

	Employee create(Employee employee);

	Employee update(String employeeId, Employee employee);

	int delete(String employeeId);
}

package com.mastery.java.task.service;

import java.util.List;
import java.util.Optional;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.rest.EmployeeController;

public interface EmployeeService {
	
	public Iterable<Employee> getAll();

	public Optional<Employee> getById(long id);

	public Optional<Employee> create(Employee employee);

	public Optional<Employee> update(long id, Employee employee);

	public void delete(long id);
	
	public Iterable<Employee> findByName(String firstName,  String lastName);
	
}

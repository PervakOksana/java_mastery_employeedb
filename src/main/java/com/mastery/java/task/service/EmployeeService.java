package com.mastery.java.task.service;

import java.util.List;
import java.util.Optional;

import com.mastery.java.task.dto.Employee;

public interface EmployeeService {
	
	public Iterable<Employee> getAll();

	public Optional<Employee> getById(long id);

	public Employee create(Employee employee);

	public Employee update(long id, Employee employee);

	public void delete(long id);
	
	public List<Employee> findByName(String firstName,  String lastName);
	
}

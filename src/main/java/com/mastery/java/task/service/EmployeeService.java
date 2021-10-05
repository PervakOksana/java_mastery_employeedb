package com.mastery.java.task.service;

import java.util.Optional;

import com.mastery.java.task.dto.Employee;

public interface EmployeeService {
	
	Iterable<Employee> getAll();

	Optional<Employee> getById(long id);

	Employee create(Employee employee);

	Employee update(long id, Employee employee);

	void delete(long id);
	
	Iterable<Employee> findAllByFirstName(String firstName);
	
	Iterable<Employee> findAllByLastName(String lastName);


}

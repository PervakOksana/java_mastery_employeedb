package com.mastery.java.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.exception.EmployeeServiceBadRequestException;
import com.mastery.java.task.service.exception.EmployeeServiceNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public Optional<Employee> getById(long id) {
		return Optional.ofNullable(employeeDao.findById(id)
				.orElseThrow(() -> new EmployeeServiceNotFoundException("Employee is not found, id=" + id)));
	}

	@Override
	public Optional<Employee> update(long id, Employee employee) {
		if (!employeeDao.existsById(id)) {
			log.error("Employee is not found, id {}", id);
			throw new EmployeeServiceNotFoundException("Employee is not found, id=" + id);
		}
		employee.setEmployeeId(id);
		return Optional.of(employeeDao.save(employee));
	}

	@Override
	public void delete(long id) {
		if (employeeDao.existsById(id)) {
			employeeDao.deleteById(id);
		} else {
			log.error("Employee is not found, id {}", id);
			throw new EmployeeServiceNotFoundException("Employee is not found, id=" + id);
		}
	}

	@Override
	public Iterable<Employee> getAll() {
		return employeeDao.findAll();
	}

	@Override
	public Optional<Employee> create(Employee employee) {
		return Optional.of(employeeDao.save(employee));
	}

	@Override
	public Iterable <Employee> findByName(String firstName, String lastName) {
		log.info("Employee, firstName {}, lastName  {}", firstName+"%", lastName+"%");
		Iterable<Employee> employees = employeeDao.findEmployeeByFirstNameContainingAndLastNameContaining(firstName, lastName); 		
		if (employees.iterator().hasNext()) {
			return employees;
		} else {
			log.error("Employee is not found, firstName {}, lastName  {}", firstName, lastName);
			throw new EmployeeServiceNotFoundException("Employee is not found, firstName = " + firstName+ ", firstName = " +lastName);
		}		
	}
}

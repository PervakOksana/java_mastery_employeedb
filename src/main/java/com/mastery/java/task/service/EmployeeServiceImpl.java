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
	public Employee update(long id, Employee employee) {
		if (!employeeDao.existsById(id)) {
			log.error("Employee is not found, id=" + id);
			throw new EmployeeServiceNotFoundException("Employee is not found, id=" + id);
		}
		return employeeDao.save(employee);

	}

	@Override
	public void delete(long id) {
		if (employeeDao.existsById(id)) {
			employeeDao.deleteById(id);
		} else {
			log.error("Employee is not found, id=" + id);
			throw new EmployeeServiceNotFoundException("Employee is not found, id=" + id);
		}

	}

	@Override
	public Iterable<Employee> getAll() {
		return employeeDao.findAll();
	}

	@Override
	public Employee create(Employee employee) {
		return employeeDao.save(employee);
	}

	@Override
	public List<Employee> findByName(String firstName, String lastName) {
		
		List<Employee> employees = new ArrayList<>();
		
		if (firstName != null && lastName != null) {
			 
			employees=employeeDao.findEmployeeByFullName(firstName, lastName);
			
			if (employees.size() <= 0) {
				log.error("Employee with first name = {} and last name = {} is not found",firstName, lastName);
				throw new EmployeeServiceNotFoundException(String.format("Employee with firstName=%s and lastName=%s is not found", firstName,  lastName));
			}			
			return employees; 
		}
		if (firstName != null && lastName == null) {
			employees=employeeDao.findEmployeeByFirstName(firstName);
			if (employees.size() <= 0) {
				log.error("Employee with first name={} is not found",firstName);
				throw new EmployeeServiceNotFoundException(String.format("Employee with firstName=%s is not found", firstName));
			}
			
			return employees;
		}
		if (lastName != null && firstName == null) {
			employees=employeeDao.findEmployeeByLastName(lastName);
			if (employees.size() <= 0) {
				log.error("Employee with last name={} is not found",lastName);
				throw new EmployeeServiceNotFoundException(String.format("Employee with lastName=%s is not found", lastName));
			}
			return employees;
		}
		if (firstName == null && lastName == null) {
			throw new EmployeeServiceBadRequestException((String.format("Name and surname can't be empty")));
		}
	
		return employees;
	}

}

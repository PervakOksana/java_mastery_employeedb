package com.mastery.java.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.exception.EmployeeServiceException;
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
				.orElseThrow(() -> new EmployeeServiceException("Employee is not found, id=" + id)));
	}

	@Override
	public Employee update(long id, Employee employee) {
		if (!employeeDao.existsById(id)) {
			log.error("Employee is not found, id=" + id);
			throw new EmployeeServiceException("Employee is not found, id=" + id);
		} 
		return employeeDao.save(employee);
		
	}

	@Override
	public void delete(long id) {
		if (employeeDao.existsById(id)) {
			employeeDao.deleteById(id);
		} else {
			log.error("Employee is not found, id=" + id);
			throw new EmployeeServiceException("Employee is not found, id=" + id);
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

}

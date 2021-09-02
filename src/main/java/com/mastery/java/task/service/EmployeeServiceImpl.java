package com.mastery.java.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.exception.EmployeeServiceException;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private EmployeeValidator employeeValidator;

	@Override
	public List<Employee> getAll() {
		return employeeDao.findAll();
	}

	@Override
	public Employee getById(String employeeId) {

		if (!employeeValidator.isNumber(employeeId) || 
				!employeeValidator.isExist(Integer.parseInt(employeeId))) {
			
			EmployeeServiceException employeeServiceException = new EmployeeServiceException(
					String.format(employeeValidator.getMessage(), employeeId));
			log.error(employeeServiceException.getMessage());
			throw employeeServiceException;
		}
		return employeeDao.findById(Integer.parseInt(employeeId));
	}

	@Override
	public Employee create(Employee employee) {
		
		if (!employeeValidator.isCorrect(employee)) {
			
			EmployeeServiceException employeeServiceException = new EmployeeServiceException(
					employeeValidator.getMessage());
			log.error(employeeServiceException.getMessage());
			throw employeeServiceException;
		}
		return employeeDao.save(employee);
	}

	@Override
	public Employee update(String employeeId, Employee employee) {

		if (!employeeValidator.isNumber(employeeId) || 
				!employeeValidator.isCorrect(employee)|| 
				!employeeValidator.isExist(Integer.parseInt(employeeId))) {
			
			EmployeeServiceException employeeServiceException = new EmployeeServiceException(
					String.format(employeeValidator.getMessage(), employeeId));
			log.error(employeeServiceException.getMessage());
			throw employeeServiceException;
		}

	
		employee.setEmployeeId(Integer.parseInt(employeeId));
		return employeeDao.update(employee);
	}

	@Override
	public int delete(String employeeId) {
		if (!employeeValidator.isNumber(employeeId) || 
				!employeeValidator.isExist(Integer.parseInt(employeeId))) {
			
			EmployeeServiceException employeeServiceException = new EmployeeServiceException(
					String.format(employeeValidator.getMessage(), employeeId));
			log.error(employeeServiceException.getMessage());
			throw employeeServiceException;
		}
		return employeeDao.deleteById(Integer.parseInt(employeeId));
	}

}

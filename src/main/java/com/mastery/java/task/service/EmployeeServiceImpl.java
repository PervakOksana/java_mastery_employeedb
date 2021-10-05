package com.mastery.java.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.exception.EmployeeServiceNotFoundException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
	public Iterable<Employee> findAllByFirstName(String firstName) {

		Iterable<Employee> employeefindAllByFirstName = StreamSupport.stream(employeeDao.findAll().spliterator(), false)
				.collect(Collectors.toList())
				.stream()
				.filter(c -> firstName.equals(c.getFirstName()))
				.collect(Collectors.toList());

		if (!employeefindAllByFirstName.iterator().hasNext()) {
			log.error("Employee with firstName={} is not found",firstName);
			throw new EmployeeServiceNotFoundException(String.format("Employee with firstName=%s is not found", firstName));
		}

		return employeefindAllByFirstName;
	}

	@Override
	public Iterable<Employee> findAllByLastName(String lastName) {

		Iterable<Employee> employeefindAllByLastName = StreamSupport.stream(employeeDao.findAll().spliterator(), false)
				.collect(Collectors.toList())
				.stream()
				.filter(c -> lastName.equals(c.getLastName()))
				.collect(Collectors.toList());

		if (!employeefindAllByLastName.iterator().hasNext()) {
			log.error("Employee with lastName={} is not found",lastName);
			throw new EmployeeServiceNotFoundException(String.format("Employee with lastName=%s is not found", lastName));
		}

		return employeefindAllByLastName;
	}

}

package com.mastery.java.task.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	public List<Employee> getAllEmployees() {
		log.info("Metod getAllEmployees was started");
		return employeeService.getAll();
	}

	@GetMapping("/{employeeId}")
	public Employee getEmployeeById(@PathVariable("employeeId") String employeeId) {
		log.info("Metod getEmployeeById was started");
		return employeeService.getById(employeeId);
	}

	@PostMapping
	public Employee createEmployee(@RequestBody Employee employee) {
		log.info("Metod createEmploye was started");
		return employeeService.create(employee);	 
	}

	@PutMapping("/{employeeId}")
	public Employee updateEmployee(@PathVariable("employeeId") String employeeId,
			@RequestBody Employee employee) {
		log.info("Metod updateEmployee was started");
		return employeeService.update(employeeId, employee);
		
	}

	@DeleteMapping("/{employeeId}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable("employeeId") String employeeId) {
		log.info("Metod deleteEmployee was started");
		employeeService.delete(employeeId);
		return ResponseEntity.ok().build();
	}
}

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
import com.mastery.java.task.service.EmployeeServiceImpl;
import java.util.Optional;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {
	 
	private static final Logger log = LogManager.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeServiceImpl employeeService;

	@GetMapping
	public Iterable<Employee> getAllEmployees() {
		log.info("Trying to get employee list");
		
		return employeeService.getAll();
	}

	@GetMapping("/{employeeId}")
	public Optional<Employee> getEmployeeById(@Valid @PathVariable("employeeId") long employeeId) {
		log.info("Trying to get employee by id {}", employeeId);
		return employeeService.getById(employeeId);
	}

	@PostMapping
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		log.info("Trying to post {}", employee);
		return employeeService.create(employee);	 
	}

	@PutMapping("/{employeeId}")
	public Employee updateEmployee(@Valid @PathVariable("employeeId") long employeeId,
			 @RequestBody @Valid Employee employee) {
		log.info("Trying to put employee by id {}", employeeId);
		return employeeService.update(employeeId, employee);
		
	}

	@DeleteMapping("/{employeeId}")
	public ResponseEntity<Void> deleteEmployee(@Valid @PathVariable("employeeId") long employeeId) {
		log.info("Trying to delete employee by id {}", employeeId);
		employeeService.delete(employeeId);
		return ResponseEntity.ok().build();
	}
}

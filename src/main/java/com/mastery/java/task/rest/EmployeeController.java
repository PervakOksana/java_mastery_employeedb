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
	public ResponseEntity<Iterable<Employee>> getAllEmployees() {
		log.info("Trying to get employee list");		
		return ResponseEntity.ok().body(employeeService.getAll());
	}

	@GetMapping("/{employeeId}")
	public ResponseEntity<Employee> getEmployeeById(@Valid @PathVariable long employeeId) {
		
		System.out.println("getEmployeeById");
		log.info("Trying to get employee by id {}", employeeId);
		return ResponseEntity.ok().body(employeeService.getById(employeeId).get());
	}
	
	@GetMapping("/first/{firstName}")
	public ResponseEntity<Iterable<Employee>> getEmployeeByFirstName(@Valid @PathVariable String firstName) {
		log.info("Trying to find employee by first name {}", firstName);
		return ResponseEntity.ok().body(employeeService.findAllByFirstName(firstName));
	}
	
	@GetMapping("/last/{lastName}")
	public ResponseEntity<Iterable<Employee>> getEmployeeByLastName(@Valid @PathVariable String lastName) {
		log.info("Trying to find employee by last name {}", lastName);
		return ResponseEntity.ok().body(employeeService.findAllByLastName(lastName));
	}

	@PostMapping
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
		log.info("Trying to post {}", employee.toString());
		return ResponseEntity.status(201).body(employeeService.create(employee));	 
	}

	@PutMapping("/{employeeId}")
	public ResponseEntity<Employee> updateEmployee(@Valid @PathVariable  long employeeId,
			 @RequestBody @Valid Employee employee) {
		log.info("Trying to put employee by id {}", employeeId);
		return ResponseEntity.ok().body(employeeService.update(employeeId, employee));
		
	}

	@DeleteMapping("/{employeeId}")
	public ResponseEntity<Void> deleteEmployee(@Valid @PathVariable long employeeId) {
		log.info("Trying to delete employee by id {}", employeeId);
		employeeService.delete(employeeId);
		return ResponseEntity.ok().build();
	}
}

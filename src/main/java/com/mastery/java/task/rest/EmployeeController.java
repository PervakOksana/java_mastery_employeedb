package com.mastery.java.task.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/employees")
@Api("Operations with employees in aplication")
@ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved list"),
		@ApiResponse(code = 400, message = "The request was bad"),
		@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
		@ApiResponse(code = 500, message = "Something went wrong, entry was not added") })
public class EmployeeController {
	
	@Autowired
	private EmployeeServiceImpl employeeService;


	@GetMapping
	@ApiOperation(value = "Getting employee list", response = Iterable.class)
	public Iterable<Employee> getAllEmployees() {
		log.info("Trying to get employee list");
		return employeeService.getAll();
	}

	@GetMapping("/{employeeId}")
	@ApiOperation(value = "Getting employee by id", response = Employee.class)
	public Employee getEmployeeById(@PathVariable long employeeId) {
		log.info("Trying to get employee by id {}", employeeId);
		return employeeService.getById(employeeId).get();
	}

	@GetMapping("/find")
	@ApiOperation(value = "Getting employee by first name and by last name", response = List.class)
	public List<Employee> getEmployeeByName(@RequestParam(required = false) String name, @RequestParam(required = false) String surname) {
		log.info("Trying to find employee by first name {} and by last name {}", name, surname);
		return employeeService.findByName(name, surname);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "Creating employee", response = Employee.class)
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		log.info("Trying to post {}", employee);
		return employeeService.create(employee);
	}

	@PutMapping("/{employeeId}")
	@ApiOperation(value = "Updating employee", response = Employee.class)
	public Employee updateEmployee(@PathVariable long employeeId, @Valid @RequestBody Employee employee) {
		log.info("Trying to put employee by id {}", employeeId);
		return employeeService.update(employeeId, employee);

	}

	@DeleteMapping("/{employeeId}")
	@ApiOperation(value = "Deleting employee")
	public void deleteEmployee(@PathVariable long employeeId) {
		log.info("Trying to delete employee by id {}", employeeId);
		employeeService.delete(employeeId);
	}
}

package com.mastery.java.task.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.service.EmployeeServiceImpl;
import com.mastery.java.task.dto.Employee.Builder;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class EmployeeControllerTest {

	@InjectMocks
	EmployeeController employeeController;

	@Mock
	EmployeeServiceImpl employeeServiceImpl;

	Employee employee1 = new Builder().setEmployeeId(1).setFirstName("Ivan").setLastName("Ivanov").setDepartmentId(8L)
			.setJobTitle("").setGender(Gender.FEMALE).build();
	Employee employee2 = new Builder().setEmployeeId(2).setFirstName("Ivan").setLastName("Ivanov").setDepartmentId(8L)
			.setJobTitle("").setGender(Gender.FEMALE).build();
	Employee employee3 = new Builder().setEmployeeId(3).setFirstName("Ivan").setLastName("Ivanov").setDepartmentId(8L)
			.setJobTitle("").setGender(Gender.FEMALE).build();

	Iterable<Employee> employees = Arrays.asList(employee1, employee2, employee3);

	@Test
	public void createEmployeeTest() {

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(employeeServiceImpl.create(any(Employee.class))).thenReturn(employee1);
		ResponseEntity<Employee> responseEntity = employeeController.createEmployee(employee1);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
		assertThat(responseEntity.getBody().getFirstName()).isEqualTo("Ivan");

	}

	@Test
	public void getAllEmployeesTest() {

		when(employeeServiceImpl.getAll()).thenReturn(employees);
		ResponseEntity<Iterable<Employee>> result = employeeController.getAllEmployees();
		assertThat(result.getBody()).isEqualTo(employees);

	}

	@Test
	public void getEmployeeByIdTest() {
		Optional<Employee> employeeO = Optional.ofNullable(employee1);
		when(employeeServiceImpl.getById(1L)).thenReturn(employeeO);
		ResponseEntity<Employee> result = employeeController.getEmployeeById(1L);
		assertThat(result.getBody()).isEqualTo(employee1);
		assertThat(result.getStatusCodeValue()).isEqualTo(200);

	}

}

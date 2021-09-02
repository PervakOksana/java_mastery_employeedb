package com.mastery.java.task.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;																
import org.springframework.boot.test.context.SpringBootTest;
import com.mastery.java.task.dao.EmployeeDaoImpl;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;

@SpringBootTest
public class EmployeeServiceTest {
	
	@Mock
	private EmployeeDaoImpl employeeDaoImpl;
	
	@Mock
	private EmployeeValidatorImpl employeeValidatorImpl;

	@InjectMocks
	private EmployeeServiceImpl employeeServiceImpl;

	private List<Employee> employeeListTest = Arrays.asList(
			new Employee(1, "Nik", "Petrov", 1L, "programmer", Gender.MALE),
			new Employee(2, "Mark", "Ilonov", 1L, "programmer", Gender.MALE),
			new Employee(3, "Ivona", "Mruk", 1L, "programmer", Gender.FEMALE),
			new Employee(4, "Petr", "Sever", 1L, "programmer", Gender.MALE));

	private Employee employeeTest = new Employee(1, "Nik", "Petrov", 1L, "programmer", Gender.MALE);

	@Test
	public void getAllTest() {	
		when(employeeDaoImpl.findAll()).thenReturn(employeeListTest);
		assertThat(employeeServiceImpl.getAll()).isEqualTo(employeeListTest);
		Mockito.verify(employeeDaoImpl).findAll();
	}

	@Test
	public void getByIdTest() {		
		when(employeeDaoImpl.findById(1)).thenReturn(employeeTest);
		when(employeeValidatorImpl.isNumber("1")).thenReturn(true);
		when(employeeValidatorImpl.isExist(1)).thenReturn(true);
		assertThat(employeeServiceImpl.getById("1")).isEqualTo(employeeTest);
		Mockito.verify(employeeDaoImpl).findById(1);
	}

	@Test
	public void deleteTest() {
		when(employeeDaoImpl.deleteById(1)).thenReturn(1);
		when(employeeValidatorImpl.isNumber("1")).thenReturn(true);
		when(employeeValidatorImpl.isExist(1)).thenReturn(true);
		assertThat(employeeServiceImpl.delete("1")).isEqualTo(1);
		Mockito.verify(employeeDaoImpl).deleteById(1);
	}

	@Test
	public void updateTest() {
		when(employeeDaoImpl.update(employeeTest)).thenReturn(employeeTest);
		when(employeeValidatorImpl.isNumber("1")).thenReturn(true);
		when(employeeValidatorImpl.isCorrect(employeeTest)).thenReturn(true);
		when(employeeValidatorImpl.isExist(1)).thenReturn(true);
		assertThat(employeeServiceImpl.update("1", employeeTest)).isEqualTo(employeeTest);
		Mockito.verify(employeeDaoImpl).update(employeeTest);
	}
	
	@Test
	public void creatrTest() {
		when(employeeDaoImpl.save(employeeTest)).thenReturn(employeeTest);
		when(employeeValidatorImpl.isNumber("1")).thenReturn(true);
		when(employeeValidatorImpl.isCorrect(employeeTest)).thenReturn(true);
		when(employeeValidatorImpl.isExist(1)).thenReturn(true);
		assertThat(employeeServiceImpl.create(employeeTest)).isEqualTo(employeeTest);
		Mockito.verify(employeeDaoImpl).save(employeeTest);
	}
}

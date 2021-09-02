package com.mastery.java.task.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Employee.Builder;
import com.mastery.java.task.dto.Gender;

@SpringBootTest
public class EmployeeDaoTest {

	@Autowired
	private EmployeeDao employeeDao;

	@BeforeEach
	public void addEmployeeToDB() {
		employeeDao.save(
				new Builder()
				.setEmployeeId(2)
				.setFirstName("Ivan")
				.setLastName("Ivanov")
				.setDepartmentId(8L)
				.setJobTitle("")
				.setGender(Gender.FEMALE)
				.build());
	}

	@AfterEach
	public void deleteEmployeeFromDB() {
		employeeDao.deleteById(employeeDao.maxEmployeeId());
	}

	@Test
	public void getAllTest() {
		assertFalse(employeeDao.findAll().isEmpty());
	}

	@Test
	public void getByIdTest() {
		assertEquals(8L, employeeDao.findById(employeeDao.maxEmployeeId()).getDepartmentId());
	}

	@Test
	public void updateTest() {
		Employee employee = new Builder()
				.setEmployeeId(employeeDao.maxEmployeeId())
				.setFirstName("Test")
				.setLastName("Test")
				.setDepartmentId(1L)
				.setJobTitle("Test")
				.setGender(Gender.FEMALE)
				.build();
		assertEquals("Test", employeeDao.update(employee).getLastName());
	}
}

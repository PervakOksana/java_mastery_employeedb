package com.mastery.java.task.service;

import com.mastery.java.task.dto.Employee;

public interface EmployeeValidator {

	public boolean isCorrect(Employee employee);
	
	public boolean isExist(int employeeId);

	public boolean isNumber(String employeeId);
	
	public String getMessage();
}

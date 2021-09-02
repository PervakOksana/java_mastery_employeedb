package com.mastery.java.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;

@Service
public class EmployeeValidatorImpl implements EmployeeValidator {

	@Autowired
	private EmployeeDao employeeDao;
	private String message;

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public boolean isCorrect(Employee employee) {

		if (employee.getDepartmentId() > 10) {
			setMessage("The DepartmentId have to be less 11");
			return false;
		}
		if (employee.getFirstName() == null) {
			setMessage("The FirstName can't be empty");
			return false;
		}
		if (!employee.getGender().name().equals("MALE") && !employee.getGender().name().equals("FEMALE")) {
			setMessage("The Gender can't be empty");
			return false;
		}
		if (employee.getJobTitle() == null) {
			setMessage("The JobTitle can't be empty");
			return false;
		}
		if (employee.getLastName() == null) {
			setMessage("The JobTitle can't be empty");
			return false;
		}
		return true;
	}

	@Override
	public boolean isNumber(String employeeId) {
		try {
			Integer.parseInt(employeeId);
		} catch (NumberFormatException ex) {
			setMessage("Employee with id = %s do not found");
			return false;
		}
		return true;
	}

	@Override
	public boolean isExist(int employeeId)  {
		if (employeeDao.findById(employeeId) == null) {
			setMessage("Employee with id = %s do not found");
			return false;
		}
		return true;
	}
}

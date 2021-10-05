package com.mastery.java.task.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EmployeeServiceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EmployeeServiceNotFoundException() {
		super();
	}
	public EmployeeServiceNotFoundException(String message) {
		super(message);
	}
	public EmployeeServiceNotFoundException(Throwable e) {
		super(e);
	}
	public EmployeeServiceNotFoundException(String message, Throwable e) {
		super(message, e);
	}
}

package com.mastery.java.task.service.exception;

public class EmployeeServiceBadRequestException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EmployeeServiceBadRequestException() {
		super();
	}

	public EmployeeServiceBadRequestException(String message) {
		super(message);
	}

	public EmployeeServiceBadRequestException(Throwable e) {
		super(e);
	}

	public EmployeeServiceBadRequestException(String message, Throwable e) {
		super(message, e);
	}
}
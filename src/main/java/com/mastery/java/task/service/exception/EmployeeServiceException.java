package com.mastery.java.task.service.exception;

public class EmployeeServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmployeeServiceException() {
		super();
	}
	public EmployeeServiceException(String message) {
		super(message);
	}
	public EmployeeServiceException(Throwable e) {
		super(e);
	}
	public EmployeeServiceException(String message, Throwable e) {
		super(message, e);
	}
}

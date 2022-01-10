package com.mastery.java.task.rest;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import com.mastery.java.task.service.exception.EmployeeServiceBadRequestException;
import com.mastery.java.task.service.exception.ExceptionInfo;
import com.mastery.java.task.service.exception.EmployeeServiceNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ControllerAdvisor  {

	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionInfo handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValid occurred: " + ex.getMessage(), ex);
        List<String> messages = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
        return new ExceptionInfo.Builder().messages(messages).timestamp(new Date()).build();
    }

	@ExceptionHandler(EmployeeServiceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ExceptionInfo handleEmployeeNotFoundException(EmployeeServiceNotFoundException ex) {
		log.error("EmployeeNotFoundException occurred: " + ex.getMessage(), ex);
		return new ExceptionInfo.Builder().messages(ex.getMessage()).timestamp(new Date()).build();
	}
	
	@ExceptionHandler(EmployeeServiceBadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionInfo handleEmployeeServiceBadRequestException(EmployeeServiceBadRequestException ex) {
		log.error("EmployeeServiceBadRequestException occurred: " + ex.getMessage(), ex);
		return  new ExceptionInfo.Builder().messages(ex.getMessage()).timestamp(new Date()).build();
	}

	@ExceptionHandler({Exception.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionInfo handleAllExceptions(Exception ex) {
		log.error("AllExceptions occurred: " + ex.getMessage(), ex);
		return new ExceptionInfo.Builder().messages("Exception").timestamp(new Date()).build();
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionInfo handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
		log.error("HttpMessageNotReadable occurred: " + ex.getMessage(), ex);
		return new ExceptionInfo.Builder().messages(ex.getMessage()).timestamp(new Date()).build();
	}

	@ExceptionHandler(TypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionInfo handleTypeMismatch(TypeMismatchException ex) {
		log.error("TypeMismatch occurred: " + ex.getMessage(), ex);
		return new ExceptionInfo.Builder().messages(ex.getMessage()).timestamp(new Date()).build();
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ExceptionInfo handleNoHandlerFoundException(NoHandlerFoundException ex) {
		log.error("NoHandlerFoundException occurred: " + ex.getMessage(), ex);
		return new ExceptionInfo.Builder().messages(ex.getMessage()).timestamp(new Date()).build();
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionInfo handleConstraintViolationException(ConstraintViolationException ex) {
		log.error("ConstraintViolationException occurred: " + ex.getMessage(), ex);
		return new ExceptionInfo.Builder().messages(ex.getMessage()).timestamp(new Date()).build();
	}

}


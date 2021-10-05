package com.mastery.java.task.service.exception;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		log.error("MethodArgumentNotValid occurred: " + ex.getMessage());

		List<String> messages = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
		ExeptionInfo exeptionInfo = new ExeptionInfo.Builder().messages(messages).timestamp(new Date()).build();
		return new ResponseEntity<>(exeptionInfo, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler({ EmployeeServiceNotFoundException.class })
	protected ResponseEntity<Object> handleEmployeeNotFoundException(EmployeeServiceNotFoundException ex,
			WebRequest request) {

		log.error("EmployeeNotFoundException occurred: " + ex.getMessage());

		ExeptionInfo exeptionInfo = new ExeptionInfo.Builder().messages(ex.getMessage()).timestamp(new Date()).build();
		return new ResponseEntity<>(exeptionInfo, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		log.error("HttpMessageNotReadable occurred: " + ex.getMessage());

		ExeptionInfo exeptionInfo = new ExeptionInfo.Builder().messages(ex.getMessage()).timestamp(new Date()).build();
		return new ResponseEntity<>(exeptionInfo, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		log.error("TypeMismatch occurred: " + ex.getMessage());

		ExeptionInfo exeptionInfo = new ExeptionInfo.Builder().messages(ex.getMessage()).timestamp(new Date()).build();
		return new ResponseEntity<>(exeptionInfo, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

		log.error("AllExceptions occurred: " + ex.getMessage());

		ExeptionInfo exeptionInfo = new ExeptionInfo.Builder().messages("Exception").timestamp(new Date()).build();
		return new ResponseEntity<>(exeptionInfo, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		log.error("NoHandlerFoundException occurred: " + ex.getMessage());

		ExeptionInfo exeptionInfo = new ExeptionInfo.Builder().messages(ex.getMessage()).timestamp(new Date()).build();
		return new ResponseEntity<>(exeptionInfo, HttpStatus.NOT_FOUND);
	}

}

//@ExceptionHandler можно обрабатывать исключения на уровне отдельного контроллера. 
//Для этого достаточно объявить метод, в котором будет содержаться вся логика обработки 
//нужного исключения, и проаннотировать его.
//
//Аннотация ControllerAdvice. Данная аннотация дает «совет» группе констроллеров 
//по определенным событиям. В нашем случае — это обработка ошибок. По умолчанию 
//применяется ко всем контроллерам, но в параметрах можно указать отпределенную группу. 
//Подбронее тут.
//
//Класс ResponseEntityExceptionHandler. Данный класс занимается обработкой ошибок. У него 
//куча методов, название которых построенно по принципу handle + название исключения. Если 
//мы хотим обработать какое-то базовое исключение, то наследуемся от этого класса и переопределяем 
//нужный метод.
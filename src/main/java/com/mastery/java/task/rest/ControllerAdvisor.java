package com.mastery.java.task.rest;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import com.mastery.java.task.service.exception.EmployeeServiceBadRequestException;
import com.mastery.java.task.service.exception.ExeptionInfo;
import com.mastery.java.task.service.exception.EmployeeServiceNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ControllerAdvisor  {

	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExeptionInfo handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValid occurred: " + ex.getMessage(), ex);
        List<String> messages = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
        return new ExeptionInfo.Builder().messages(messages).timestamp(new Date()).build();
    }

	@ExceptionHandler(EmployeeServiceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ExeptionInfo handleEmployeeNotFoundException(EmployeeServiceNotFoundException ex) {
		log.error("EmployeeNotFoundException occurred: " + ex.getMessage(), ex);
		return new ExeptionInfo.Builder().messages(ex.getMessage()).timestamp(new Date()).build();
	}
	
	@ExceptionHandler(EmployeeServiceBadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExeptionInfo handleEmployeeServiceBadRequestException(EmployeeServiceBadRequestException ex) {
		log.error("EmployeeServiceBadRequestException occurred: " + ex.getMessage(), ex);
		return  new ExeptionInfo.Builder().messages(ex.getMessage()).timestamp(new Date()).build();
	}

	@ExceptionHandler({Exception.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExeptionInfo handleAllExceptions(Exception ex) {
		log.error("AllExceptions occurred: " + ex.getMessage(), ex);
		return new ExeptionInfo.Builder().messages("Exception").timestamp(new Date()).build();
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExeptionInfo handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
		log.error("HttpMessageNotReadable occurred: " + ex.getMessage(), ex);
		return new ExeptionInfo.Builder().messages(ex.getMessage()).timestamp(new Date()).build();
	}

	@ExceptionHandler(TypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExeptionInfo handleTypeMismatch(TypeMismatchException ex) {
		log.error("TypeMismatch occurred: " + ex.getMessage(), ex);
		return new ExeptionInfo.Builder().messages(ex.getMessage()).timestamp(new Date()).build();
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ExeptionInfo handleNoHandlerFoundException(NoHandlerFoundException ex) {
		log.error("NoHandlerFoundException occurred: " + ex.getMessage(), ex);
		return new ExeptionInfo.Builder().messages(ex.getMessage()).timestamp(new Date()).build();
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
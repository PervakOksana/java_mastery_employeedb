package com.mastery.java.task.service.exception;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", status.value());

		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
		body.put("errors", errors);

		return new ResponseEntity<>(body, headers, status);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public void constraintViolationException(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}

	@ExceptionHandler(EmployeeServiceException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleEmployeeServiceException(Exception ex) {
		log.error("Error in handleEmployeeServiceException - " + ex);
		return ex.getMessage();
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
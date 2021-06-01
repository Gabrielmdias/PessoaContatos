package com.elotech.shared;

import javax.validation.ConstraintViolationException;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

	@ExceptionHandler(value = { ConstraintViolationException.class })
	protected ResponseEntity<Object> handleConflict(ConstraintViolationException ex, WebRequest request) {
		LOG.error(ex.getMessage());
		if(ex.getMessage().contains("CPF")) {
			return handleExceptionInternal(ex, "CPF inválido", new HttpHeaders(), HttpStatus.CONFLICT, request);
		}
		if(ex.getMessage().contains("dataNascimento")) {
			return handleExceptionInternal(ex, "Data inválida", new HttpHeaders(), HttpStatus.CONFLICT, request);
		}
		if(ex.getMessage().contains("email")) {
			return handleExceptionInternal(ex, "Email inválido", new HttpHeaders(), HttpStatus.CONFLICT, request);
		}
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	@ExceptionHandler(value = { JdbcSQLIntegrityConstraintViolationException.class })
	protected ResponseEntity<Object> handleConflict(JdbcSQLIntegrityConstraintViolationException ex, WebRequest request) {
		LOG.error(ex.getMessage());
		return handleExceptionInternal(ex, "Dados já existem", new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
}
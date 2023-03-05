package com.valdir.helpdesk.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.valdir.helpdesk.services.exception.DataIntegrityViolstionExeption;
import com.valdir.helpdesk.services.exception.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFoundException(
			ObjectNotFoundException exception,
			HttpServletRequest request){
		
		StandardError standardError = new StandardError(
				System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value(),
				"Object Not Found", 
				exception.getMessage(), 
				request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
	}
	
	@ExceptionHandler(DataIntegrityViolstionExeption.class)
	public ResponseEntity<StandardError> dataIntegrityViolstionExeption(
			DataIntegrityViolstionExeption exception,
			HttpServletRequest request){
		
		StandardError standardError = new StandardError(
				System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(),
				"Violação de dados!", 
				exception.getMessage(), 
				request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validationErros(
			MethodArgumentNotValidException exception,
			HttpServletRequest request){
		
		ValidationError validationError = new ValidationError(
				System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(),
				"Validation Erro",
				"Erro na validação dos campos",
				request.getRequestURI()
				);	
		
		for(FieldError erro : exception.getBindingResult().getFieldErrors()) {
			validationError.addErros(erro.getField(), erro.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
	}
}

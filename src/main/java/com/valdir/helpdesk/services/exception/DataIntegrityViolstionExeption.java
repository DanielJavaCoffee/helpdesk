package com.valdir.helpdesk.services.exception;

public class DataIntegrityViolstionExeption extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DataIntegrityViolstionExeption(String message, Throwable cause) {
		super(message, cause);
	}

	public DataIntegrityViolstionExeption(String message) {
		super(message);
	}
}

package com.valdir.helpdesk.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessaage> erros = new ArrayList<>();

	public ValidationError() {
		super();
	}

	public ValidationError(Long timestamp, Integer status, String erro, String message, String path) {
		super(timestamp, status, erro, message, path);
	}

	public List<FieldMessaage> getErros() {
		return erros;
	}

	public void addErros(String fildName, String message) {
		this.erros.add(new FieldMessaage(fildName, message));
	}
}

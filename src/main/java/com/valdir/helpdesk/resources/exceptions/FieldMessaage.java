package com.valdir.helpdesk.resources.exceptions;

import java.io.Serializable;

public class FieldMessaage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String fielMessage;
	private String message;
	
	public FieldMessaage() {
		super();
	}

	public FieldMessaage(String fielMessage, String message) {
		super();
		this.fielMessage = fielMessage;
		this.message = message;
	}

	public String getFielMessage() {
		return fielMessage;
	}

	public void setFielMessage(String fielMessage) {
		this.fielMessage = fielMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

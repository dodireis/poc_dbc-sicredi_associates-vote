package com.sicredi.associatesvote.controller.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandartError {

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors;

	public ValidationError(Integer status, String msg, Long timestamp) {
		super(status, msg, timestamp);
		this.errors = new ArrayList<FieldMessage>();
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addErrors(String fieldName, String message) {
		this.errors.add(new FieldMessage(fieldName, message));
	}

}

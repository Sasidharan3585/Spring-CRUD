package com.example.CrudService.exceptions;

public class StudentIdAlreadyExistsException extends Exception {
	
	
	public StudentIdAlreadyExistsException() {
	}

	public StudentIdAlreadyExistsException(String message) {
		super(message);
	}

	public StudentIdAlreadyExistsException(Throwable cause) {
		super(cause);
	}

	public StudentIdAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public StudentIdAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}

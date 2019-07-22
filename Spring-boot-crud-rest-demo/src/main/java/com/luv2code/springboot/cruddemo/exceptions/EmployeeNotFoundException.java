package com.luv2code.springboot.cruddemo.exceptions;

public class EmployeeNotFoundException extends RuntimeException {

	public EmployeeNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public EmployeeNotFoundException(String message) {
		super(message);
	}

	public EmployeeNotFoundException(Throwable cause) {
		super(cause);
	}

}

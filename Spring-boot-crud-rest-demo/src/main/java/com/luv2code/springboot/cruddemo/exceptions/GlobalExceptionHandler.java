package com.luv2code.springboot.cruddemo.exceptions;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private CustomErrorResponse customErrorResponse;
	
	@Autowired
	public GlobalExceptionHandler(CustomErrorResponse theCustomErrorResponse) {
		this.customErrorResponse = theCustomErrorResponse;
	}

	@ExceptionHandler
	public ResponseEntity<CustomErrorResponse> handleException(EmployeeNotFoundException enfe) throws UnknownHostException {
		customErrorResponse.setCode(HttpStatus.NOT_FOUND.value());
		customErrorResponse.setDescription(enfe.getMessage());
		customErrorResponse.setOrigin(InetAddress.getLocalHost().getHostAddress());
		customErrorResponse.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<CustomErrorResponse>(customErrorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<CustomErrorResponse> handleException(Exception e) throws UnknownHostException {
		customErrorResponse.setCode(HttpStatus.BAD_REQUEST.value());
		customErrorResponse.setDescription(e.getMessage());
		customErrorResponse.setOrigin(InetAddress.getLocalHost().getHostAddress());
		customErrorResponse.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<CustomErrorResponse>(customErrorResponse, HttpStatus.BAD_REQUEST);
	}
}

package com.restful.webservice;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends RuntimeException {

	public EmployeeNotFoundException(String message) {
		super(message+" is not found!!");
		// TODO Auto-generated constructor stub
	}

}

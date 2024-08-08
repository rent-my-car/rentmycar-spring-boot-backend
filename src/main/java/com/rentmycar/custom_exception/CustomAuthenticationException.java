package com.rentmycar.custom_exception;

public class CustomAuthenticationException extends RuntimeException {
	public CustomAuthenticationException(String mesg) {
		super(mesg);
	}
}

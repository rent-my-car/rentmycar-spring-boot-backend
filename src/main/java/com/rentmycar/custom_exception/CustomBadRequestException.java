package com.rentmycar.custom_exception;

public class CustomBadRequestException extends RuntimeException {

	public CustomBadRequestException() {
		// TODO Auto-generated constructor stub
	}

	public CustomBadRequestException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CustomBadRequestException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public CustomBadRequestException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CustomBadRequestException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}

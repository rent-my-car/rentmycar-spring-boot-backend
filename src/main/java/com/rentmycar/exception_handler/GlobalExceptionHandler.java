package com.rentmycar.exception_handler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rentmycar.custom_exception.PasswordMismatchException;
import com.rentmycar.custom_exception.ResourceNotFoundException;
import com.rentmycar.custom_exception.UserAlreadyExistsException;
import com.rentmycar.dto.ApiResponseDto;

@RestControllerAdvice // =@ControllerAdvice +
//@ResponseBody added on ret types of exc handling methods
public class GlobalExceptionHandler {
	// add exc handling methods
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
		System.out.println("in res not found exc ");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseDto(e.getMessage()));
	}

	// add catch-all exc handling method
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleAnyException(RuntimeException e) {
		System.out.println("in catch all " + e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(e.getMessage()));
	}

	// add @RequestBody validation exc handling method
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		System.out.println("in catch all " + e);
		List<FieldError> list = e.getFieldErrors();
		/*
		 * Convert List<FieldError> -> Map<K,V> K -FieldError: getField() V -FieldError:
		 * getDefaultMessage()
		 */
		Map<String, String> map = list.stream() // Stream<FieldError>
				.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));// f -> f.getField()
		return map;
	}

	// handle password mismatch during registraion
	@ExceptionHandler(PasswordMismatchException.class)
	public ResponseEntity<?> handlePasswordMismatchException(PasswordMismatchException e) {
		System.out.println("in res of pass mismatch exc ");
		return ResponseEntity.badRequest().body(new ApiResponseDto(e.getMessage()));

	}

	// handle UserAlreadyExistsException 
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<?> handleUserAlreadyExistsException(UserAlreadyExistsException e){
		System.out.println("in res of UserAlreadyExistsException ");
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseDto(e.getMessage()));
	}
}

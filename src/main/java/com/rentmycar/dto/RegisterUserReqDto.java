package com.rentmycar.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.rentmycar.entity.UserRoleEnum;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterUserReqDto {
	
	private Long id;

	@NotBlank(message = "firstName must not be blannk")
	String firstName; // VARCHAR(20),NOT NULL

	@NotBlank(message = "last name must have a value")
	String lastName; // VARCHAR(20),NOT NULL

	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number. It should be exactly 10 digits long and start with 6, 7, 8, or 9.")
	String mobile; // VARCHAR(12),UNIQUE NOT NULL

	@NotBlank
	@Email(message = "invalid email id ")
	String email; // VARCHAR(25),UNIQUE NOT NULL

	@NotBlank
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Blank or Invalid password!!!!")
	String password; // VARCHAR(70), NOT NULL

	@NotBlank
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Blank or Invalid password!!!!")
	String confirmPassword; // VARCHAR(70), NOT NULL
	
	@NotNull
	private UserRoleEnum roleEnum;
}

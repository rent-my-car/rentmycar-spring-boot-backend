package com.rentmycar.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.rentmycar.entity.UserRoleEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignInRequestDto {

	@NotEmpty(message = "Email must be supplied !")
	@Email(message = "Invalid Email format")
	private String email;
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Blank or Invalid password!!!!")
	private String password;
	
	private UserRoleEnum roleEnum;
}

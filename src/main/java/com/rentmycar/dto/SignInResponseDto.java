package com.rentmycar.dto;

import com.rentmycar.entity.UserRoleEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInResponseDto extends BaseDto {

	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	private UserRoleEnum roleEnum;
	
}

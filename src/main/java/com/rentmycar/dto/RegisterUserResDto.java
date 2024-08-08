package com.rentmycar.dto;

import com.rentmycar.entity.UserRoleEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserResDto extends BaseDto {
	private Long id;
	String firstName; // VARCHAR(20),NOT NULL
	String lastName; // VARCHAR(20),NOT NULL
	String mobile; // VARCHAR(12),UNIQUE NOT NULL
	String email; // VARCHAR(25),UNIQUE NOT NULL
	private UserRoleEnum roleEnum;
}

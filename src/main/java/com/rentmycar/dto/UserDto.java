package com.rentmycar.dto;

import com.rentmycar.entity.UserRoleEnum;

import lombok.Data;

@Data
public class UserDto extends BaseDto {

	private String firstName;

	private String lastName;

	private String mobile;

	private String email;

	private UserRoleEnum roleEnum;

}

package com.rentmycar.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateBasicUserDetailsDto {
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	private String password;
}

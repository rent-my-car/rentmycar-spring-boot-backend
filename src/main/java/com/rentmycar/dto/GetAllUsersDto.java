package com.rentmycar.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class GetAllUsersDto extends BaseDto {
	private Long id;
	private String firstName; // VARCHAR(20),NOT NULL
	private String lastName; // VARCHAR(20),NOT NULL
	private String mobile; // VARCHAR(12),UNIQUE NOT NULL
	private String email; // VARCHAR(25),UNIQUE NOT NULL
	// VARCHAR(70), NOT NULL
}

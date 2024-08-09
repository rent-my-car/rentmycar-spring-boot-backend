package com.rentmycar.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDetailsResponseDto extends BaseDto{

	private String firstName;
	
	private String lastName;
	
	private String mobile;
	
	private String email;
	
	private String password;
	
	private DrivingLicenseDto drivingLicenseDto;
}

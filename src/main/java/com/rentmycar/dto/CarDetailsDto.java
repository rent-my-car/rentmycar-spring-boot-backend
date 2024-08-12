package com.rentmycar.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CarDetailsDto extends CarDto {

	@NotBlank
	int spareTyreCount;

	@NotBlank
	int kmDriven; // int,not null
	
	@NotBlank
	String registrationNo; // varchar(20),not null
	
	@NotNull
	int fuelMeter; // tinyint(1-10),not null
	

}

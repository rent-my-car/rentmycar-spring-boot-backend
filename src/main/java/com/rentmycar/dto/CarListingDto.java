package com.rentmycar.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CarListingDto extends BaseDto {

	@NotBlank
	int kmDriven; // int,not null

	@NotBlank
	int fuelMeter; // tinyint(1-10),not null

	String carImage; // varchar(20),
  
	@NotBlank
	int noOfTrips;

	@NotBlank
	Boolean isAvailable; // Boolean,default-true

	@NotBlank
	Boolean isDeleted; // Boolean,default-false

	@NotBlank
	Boolean isApproved; // Boolean,default-false

	@NotBlank
	String registrationNo; // varchar(20),not null

	@NotBlank
	int spareTyreCount; // tinyint,not null
}

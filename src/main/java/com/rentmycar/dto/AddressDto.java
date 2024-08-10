package com.rentmycar.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class AddressDto {

	private Long id;

	@NotBlank
	private String adrLine1; // varchar(100) not null

	private String adrLine2; // varchar(100)

	@NotBlank
	@Pattern(regexp = "^\\d{6}$", message = "Must be a 6-digit number")
	private String pincode; // char(10),not null

	@NotBlank(message = "city should not be blank")
	private String city; // varchar(20),not null

	@NotBlank(message = "state should not be blank")
	private String state; // varchar(20),not null

	@NotBlank(message = "country should not be blank")
	private String country; // varchar(20),not null

}

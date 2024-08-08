package com.rentmycar.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.rentmycar.entity.LicenseClassEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DrivingLicenseDto{

	private Long id;
	
	@NotNull(message = "Driving license number cannot be null")
    @Pattern(regexp = "^[A-Z0-9-]+$", message = "Driving license number must only contain uppercase letters, numbers, and dashes")
    @Size(min = 5, max = 20, message = "Driving license number must be between 5 and 20 characters")
	private String drivingLicenseNo;
	
	@NotNull(message = "Issue date cannot be null")
	private LocalDate issueDate;

	@NotNull(message = "Expiration date cannot be null")
	private LocalDate expirationDate;
	
	@NotNull(message = "License class cannot be null")
	private LicenseClassEnum licenseClassEnum;
}

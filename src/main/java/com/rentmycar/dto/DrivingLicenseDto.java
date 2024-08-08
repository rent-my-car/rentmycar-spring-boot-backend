package com.rentmycar.dto;

import java.time.LocalDate;

import com.rentmycar.entity.LicenseClassEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DrivingLicenseDto{

	private Long id;
	
	private String drivingLicenseNo;
	
	private LocalDate issueDate;

	private LocalDate expirationDate;
	
	private LicenseClassEnum licenseClassEnum;
}

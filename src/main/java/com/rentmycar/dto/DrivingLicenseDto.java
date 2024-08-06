package com.rentmycar.dto;

import java.time.LocalDate;

import com.rentmycar.entity.LicenseClassEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class DrivingLicenseDto extends BaseDto{

	private String drivingLicenseNo;	
	private LocalDate issueDate;
	private LocalDate expirationDate;
	private LicenseClassEnum licenseClassEnum;
}

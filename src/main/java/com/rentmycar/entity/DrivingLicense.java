package com.rentmycar.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Embeddable
public class DrivingLicense {

	@Column(name = "license_no", length = 25, unique = true,nullable = false)
	private String drivingLicenseNo;
	
	@Column(name = "license_issue_date",nullable = false)
	private LocalDate issueDate;
	
	@Column(name = "license_expiry_date",nullable = false)
	private LocalDate expirationDate;
	
	@Enumerated(EnumType.STRING) 
	@Column(length = 10,nullable = false)
	private LicenseClassEnum licenseClassEnum; 

}

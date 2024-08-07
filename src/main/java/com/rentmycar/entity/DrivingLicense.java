package com.rentmycar.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "driving_license")
public class DrivingLicense extends BaseEntity {

	@Column(name = "license_no", length = 25, unique = true, nullable = false)
	private String drivingLicenseNo;

	@Column(name = "license_issue_date", nullable = false)
	private LocalDate issueDate;

	@Column(name = "license_expiry_date", nullable = false)
	private LocalDate expirationDate;

	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	private LicenseClassEnum licenseClassEnum;

//***************************************************************************************************
	// DrivingLicense 1 <----> 1 User
	@OneToOne(fetch = FetchType.LAZY) // mandatory
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

//***************************************************************************************************

}

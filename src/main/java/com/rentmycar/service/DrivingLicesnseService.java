package com.rentmycar.service;

import com.rentmycar.dto.DrivingLicenseDto;

public interface DrivingLicesnseService {

	// add dl with userid
	public DrivingLicenseDto addDrivingLicense(DrivingLicenseDto drivingLicenseDto, Long userId);
	
	//update dl with userId
	public DrivingLicenseDto updateDrivingLicense(DrivingLicenseDto drivingLicenseDto, Long userId);
}

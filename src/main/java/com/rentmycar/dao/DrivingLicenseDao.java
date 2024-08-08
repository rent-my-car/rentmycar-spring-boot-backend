package com.rentmycar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentmycar.entity.DrivingLicense;

public interface DrivingLicenseDao extends JpaRepository<DrivingLicense, Long> {

	// search driving license by license number which is unique
	public boolean existsByDrivingLicenseNo(String drivingLicenseNo);
}

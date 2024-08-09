package com.rentmycar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentmycar.dto.DrivingLicenseDto;
import com.rentmycar.service.DrivingLicesnseService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/dl")
public class DrivingLicesnseController {

	@Autowired
	DrivingLicesnseService drivingLicesnseService;

	// add driving license by user id
	@PostMapping("/{userId}")
	@Operation(description = "add driving licesne by user id")
	public ResponseEntity<?> addDl(@PathVariable Long userId, @RequestBody DrivingLicenseDto drivingLicenseDto) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(drivingLicesnseService.addDrivingLicense(drivingLicenseDto, userId));
	}
	
	// update driving license by user id
		@PutMapping("/{userId}/update/{dlId}")
		@Operation(description = "update driving license by user id")
		public ResponseEntity<?> updateDl(@PathVariable Long userId,
				@RequestBody DrivingLicenseDto drivingLicenseDto){
			return ResponseEntity.ok(drivingLicesnseService.updateDrivingLicense(drivingLicenseDto, userId));
		}
}

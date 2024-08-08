package com.rentmycar.dto;

import lombok.Data;

@Data
public class RegisterUserWithDlResDto extends RegisterUserResDto {

	DrivingLicenseDto drivingLicenseDto;

}

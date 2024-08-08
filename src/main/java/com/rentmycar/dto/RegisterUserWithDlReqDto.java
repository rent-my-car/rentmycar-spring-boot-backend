package com.rentmycar.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserWithDlReqDto extends RegisterUserReqDto {

	@NotNull
	DrivingLicenseDto drivingLicenseDto;
}

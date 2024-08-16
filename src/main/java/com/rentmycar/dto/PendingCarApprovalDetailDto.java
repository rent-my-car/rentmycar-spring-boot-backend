package com.rentmycar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PendingCarApprovalDetailDto extends GetCarListingResponseDto {
	private UserDto owner;

	public PendingCarApprovalDetailDto(CarDetailsDto carDetailsDto, CarPricingDto carPricingDto,
			CarFeaturesDto carFeaturesDto, AddressDto carAddressDto, UserDto owner) {
		super(carDetailsDto, carPricingDto, carFeaturesDto, carAddressDto);
		this.owner = owner;
	}

}

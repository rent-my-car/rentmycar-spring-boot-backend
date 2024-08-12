package com.rentmycar.dto;

import java.util.List;

import lombok.Data;

@Data
public class DeleteAddressResDto extends AddressDto {

	List<CarListingDto> carListingDtoList;

}

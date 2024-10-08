package com.rentmycar.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rentmycar.custom_exception.ApiException;
import com.rentmycar.dto.AddressDto;
import com.rentmycar.service.AddressService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	AddressService addressService;

	// add address by user id
	@PostMapping("/{userId}")
	public ResponseEntity<?> addAddress(@RequestBody AddressDto addressDto, @PathVariable Long userId) {
		return ResponseEntity.status(HttpStatus.CREATED).body(addressService.addAddress(addressDto, userId)
				.orElseThrow(() -> new ApiException("inernal server error")));
	}

	// get address by address id
	@Operation(description = "get address by address id")
	@GetMapping("/{addressId}")
	public ResponseEntity<?> getAddressByAddressId(@PathVariable Long addressId) {
		return ResponseEntity.status(HttpStatus.OK).body(addressService.getAddressByAddressId(addressId)
				.orElseThrow(() -> new ApiException("interanl server error")));
	}

	// get address list by user id
	@Operation(description = " get address list by user id")
	@GetMapping("/get_all/{userId}")
	public ResponseEntity<?> getAddressListbyUSerId(@PathVariable Long userId) {
		return ResponseEntity.status(HttpStatus.OK).body(addressService.getAddressListbyUSerId(userId)
				.orElseThrow(() -> new ApiException("internal server error")));
	}

	// update address by address id
	@Operation(description = "udpate address by address id")

  @PutMapping
	public ResponseEntity<?> updateAddressbyAddressId(@RequestBody @Valid AddressDto addressDto,
			@RequestHeader Long addressId) {
//		addressDto.setId(Long.parseLong(addressId));
		addressDto.setId(addressId);
		return ResponseEntity.status(HttpStatus.OK).body(addressService.updateAddressbyAddressId(addressDto)
				.orElseThrow(() -> new ApiException("internal server error")));

	}

	// soft delete address by address id
	@PatchMapping("/delete/{addressId}")
	public ResponseEntity<?> deleteAddressByAddressId(@PathVariable Long addressId) {

		return ResponseEntity.status(HttpStatus.OK).body(addressService.deleteAddressByAddressId(addressId)
				.orElseThrow(() -> new ApiException("internal server error")));
	}


	// get distinct city names
	@GetMapping("/cities")
	public ResponseEntity<?> getCities() {

		return ResponseEntity.status(HttpStatus.OK).body(
				addressService.getdistinctCityNames().orElseThrow(() -> new ApiException("internal server error")));
	}
}

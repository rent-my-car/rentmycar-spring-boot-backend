package com.rentmycar.controller;

import javax.validation.constraints.Max;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentmycar.service.GuestService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/user/guest")
public class GuestController {

	@Autowired
	private GuestService guestService;

	@GetMapping("/profile/{guestId}")
	@Operation(description = "getProfileDetails By Id")
	public ResponseEntity<?> getGuestProfileDetails(@PathVariable @Max(100) Long guestId) {
		System.out.println("In Guest Profile" + guestId);
		return ResponseEntity.ok(guestService.getGuestProfileDetails(guestId));
	}

	@GetMapping("/get_all_guests")
	public ResponseEntity<?> getAllGuests() {

		return ResponseEntity.ok(guestService.getAllGuests());

	}

}

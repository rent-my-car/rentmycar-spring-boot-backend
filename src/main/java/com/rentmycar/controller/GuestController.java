package com.rentmycar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rentmycar.service.GuestService;

@RestController
@RequestMapping("/user")
public class GuestController {

@Autowired
private GuestService guestService;	

	@GetMapping("/get_all_guests")
	public ResponseEntity<?> getAllGuests() {

		return ResponseEntity.ok(guestService.getAllGuests());

	}

}

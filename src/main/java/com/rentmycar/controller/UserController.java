package com.rentmycar.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import com.rentmycar.dto.SignInRequest;
import com.rentmycar.service.GuestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/guest")
public class UserController {
	@Autowired
	private GuestService guestService;
	
	@PostMapping("/login")
	public ResponseEntity<?> signInGuest(@RequestBody 
			@Valid SignInRequest request) {
		System.out.println("in sign in " + request);
		
			return ResponseEntity
					.ok(guestService.authenticateUser(request));
		
	}
}

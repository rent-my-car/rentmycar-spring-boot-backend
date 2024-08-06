package com.rentmycar.service;

import com.rentmycar.dto.SignInRequest;
import com.rentmycar.dto.SignInResponse;

public interface GuestService {

	//sign in
	SignInResponse authenticateUser(SignInRequest request);

}

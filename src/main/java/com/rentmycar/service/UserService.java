package com.rentmycar.service;

import com.rentmycar.dto.SignInRequest;
import com.rentmycar.dto.SignInResponse;

public interface UserService {

	SignInResponse authenticateUser(SignInRequest request);
}

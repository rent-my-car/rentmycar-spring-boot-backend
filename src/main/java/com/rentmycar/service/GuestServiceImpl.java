package com.rentmycar.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rentmycar.custom_exception.AuthenticationException;
import com.rentmycar.dao.GuestDao;
import com.rentmycar.dto.SignInRequest;
import com.rentmycar.dto.SignInResponse;
import com.rentmycar.entity.Guest;

@Service
@Transactional
public class GuestServiceImpl implements GuestService{
	@Autowired
	private GuestDao guestDao;

	@Autowired
	private ModelMapper mapper;
	
	@Override
	public SignInResponse authenticateUser(SignInRequest request) {
		Guest guestEntity = guestDao.findByEmailAndPassword(request.getEmail(), request.getPassword())
				.orElseThrow(() -> new AuthenticationException("Invalid Email Or Password"));
		// => valid login
		return mapper.map(guestEntity, SignInResponse.class);
	
	}

}

package com.rentmycar.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentmycar.custom_exceptions.AuthenticationException;
import com.rentmycar.dao.UserDao;
import com.rentmycar.dto.SignInRequest;
import com.rentmycar.dto.SignInResponse;
import com.rentmycar.entity.User;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public SignInResponse authenticateUser(SignInRequest request) {
		User userEntity = userDao.findByEmailAndPasswordAndRoleEnum(request.getEmail(),request.getPassword(),request.getRoleEnum())
				.orElseThrow(() -> new AuthenticationException("Invalid Email or Password !"));
		//valid login
		return mapper.map(userEntity, SignInResponse.class);
	}

}

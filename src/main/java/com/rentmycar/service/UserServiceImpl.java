package com.rentmycar.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentmycar.custom_exception.CustomAuthenticationException;
import com.rentmycar.dao.UserDao;
import com.rentmycar.dto.SignInRequestDto;
import com.rentmycar.dto.SignInResponseDto;
import com.rentmycar.entity.User;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public Optional<SignInResponseDto> authenticateUser(SignInRequestDto signInRequestDto) {
		User userEntity = userDao.findByEmailAndPasswordAndRoleEnum(signInRequestDto.getEmail(),signInRequestDto.getPassword(),signInRequestDto.getRoleEnum())
				.orElseThrow(() -> new CustomAuthenticationException("Invalid Email or Password !"));
		 if (userEntity.getIsDeleted()) {
		        throw new CustomAuthenticationException("User is Deactivated!");
		    }else
		//valid login
		return Optional.of(mapper.map(userEntity, SignInResponseDto.class));
	}


}

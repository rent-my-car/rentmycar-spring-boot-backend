package com.rentmycar.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentmycar.custom_exception.AuthenticationException;
import com.rentmycar.custom_exception.ResourceNotFoundException;
import com.rentmycar.dao.UserDao;
import com.rentmycar.dto.DrivingLicenseDto;
import com.rentmycar.dto.GuestDetailsResponseDto;
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
				.orElseThrow(() -> new AuthenticationException("Invalid Email or Password !"));
		//valid login
		return Optional.of(mapper.map(userEntity, SignInResponseDto.class));
	}

	@Override
	public Optional<GuestDetailsResponseDto> getGuestProfileDetails(Long guestId) {
		User userEntity = userDao.findById(guestId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid User Id !"));
		System.out.println(userEntity.getDrivingLicense().getDrivingLicenseNo());
		userEntity.getDrivingLicense().getIssueDate();
		DrivingLicenseDto drivingLicenseDto = mapper.map(userEntity.getDrivingLicense(),DrivingLicenseDto.class);
		GuestDetailsResponseDto guestDetailsResponseDto = mapper.map(userEntity, GuestDetailsResponseDto.class);
		guestDetailsResponseDto.setDrivingLicenseDto(drivingLicenseDto);
		return Optional.of(guestDetailsResponseDto);
	}

}

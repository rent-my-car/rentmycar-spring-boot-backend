package com.rentmycar.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentmycar.custom_exception.CustomAuthenticationException;
import com.rentmycar.custom_exception.CustomAuthorizationException;
import com.rentmycar.custom_exception.ResourceNotFoundException;
import com.rentmycar.dao.UserDao;
import com.rentmycar.dto.DrivingLicenseDto;
import com.rentmycar.dto.GetAllUsersDto;
import com.rentmycar.dto.UserDetailsResponseDto;
import com.rentmycar.entity.User;
import com.rentmycar.entity.UserRoleEnum;



@Service
@Transactional
public class GuestServiceImpl implements GuestService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ModelMapper mapper;
	
	
	//get all guests
	@Override
	public List<GetAllUsersDto> getAllGuests() {
		List<User> guestList = userDao.findByRoleEnum(UserRoleEnum.ROLE_GUEST).orElseThrow(()->new ResourceNotFoundException("Guest List is Empty!!"));
		TypeToken<List<GetAllUsersDto>> getallGuestDtoToken = new TypeToken<>() {};
		return(mapper.map(guestList, getallGuestDtoToken.getType()));
		
	}
}

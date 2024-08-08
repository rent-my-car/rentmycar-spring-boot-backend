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
import com.rentmycar.dto.GuestDetailsResponseDto;
import com.rentmycar.entity.User;
import com.rentmycar.entity.UserRoleEnum;



@Service
@Transactional
public class GuestServiceImpl implements GuestService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ModelMapper mapper;
	
	//method to Show Guest Profile details by Id
	@Override
	public Optional<GuestDetailsResponseDto> getGuestProfileDetails(Long guestId) {
		User userEntity = userDao.findById(guestId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid User Id !"));

		if (userEntity.getRoleEnum().equals(UserRoleEnum.GUEST)) {
			if (userEntity.getIsDeleted()) {
				throw new CustomAuthenticationException("Guest is De-Activated !");
			}
			System.out.println(userEntity.getDrivingLicense().getDrivingLicenseNo());
			userEntity.getDrivingLicense().getIssueDate();
			DrivingLicenseDto drivingLicenseDto = mapper.map(userEntity.getDrivingLicense(), DrivingLicenseDto.class);
			GuestDetailsResponseDto guestDetailsResponseDto = mapper.map(userEntity, GuestDetailsResponseDto.class);
			guestDetailsResponseDto.setDrivingLicenseDto(drivingLicenseDto);
			return Optional.of(guestDetailsResponseDto);
		} else {
				throw new CustomAuthorizationException("Not A Guest Role !");
			}
	}


	//get all guests
	@Override
	public List<GetAllUsersDto> getAllGuests() {
		List<User> guestList = userDao.findByRoleEnum(UserRoleEnum.GUEST).orElseThrow(()->new ResourceNotFoundException("Guest List is Empty!!"));
		TypeToken<List<GetAllUsersDto>> getallGuestDtoToken = new TypeToken<>() {};
		return(mapper.map(guestList, getallGuestDtoToken.getType()));
		
	}
}

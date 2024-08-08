package com.rentmycar.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentmycar.custom_exception.CustomAuthenticationException;
import com.rentmycar.custom_exception.CustomAuthorizationException;
import com.rentmycar.custom_exception.ResourceNotFoundException;
import com.rentmycar.dao.GuestDao;
import com.rentmycar.dto.DrivingLicenseDto;
import com.rentmycar.dto.GuestDetailsResponseDto;
import com.rentmycar.entity.User;
import com.rentmycar.entity.UserRoleEnum;

@Service
@Transactional
public class GuestServiceImpl implements GuestService {

	@Autowired
	private GuestDao guestDao;

	@Autowired
	private ModelMapper mapper;
	
	//method to Show Guest Profile details by Id
	@Override
	public Optional<GuestDetailsResponseDto> getGuestProfileDetails(Long guestId) {
		User userEntity = guestDao.findById(guestId)
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

}

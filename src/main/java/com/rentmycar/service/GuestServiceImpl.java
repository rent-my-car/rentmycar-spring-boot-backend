package com.rentmycar.service;

import java.util.Optional;

import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rentmycar.dto.DrivingLicenseDto;
import com.rentmycar.custom_exception.ResourceNotFoundException;
import com.rentmycar.dao.GuestDao;
import com.rentmycar.dto.GuestDetailsResponseDto;
import com.rentmycar.entity.User;

@Service
@Transactional
public class GuestServiceImpl implements GuestService {

	@Autowired
	private GuestDao guestDao;

	@Autowired
	private ModelMapper mapper;

	@Override
	public Optional<GuestDetailsResponseDto> getGuestProfileDetails(Long guestId) {
		User userEntity = guestDao.findById(guestId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid User Id !"));
		System.out.println(userEntity.getDrivingLicense().getDrivingLicenseNo());
		userEntity.getDrivingLicense().getIssueDate();
		DrivingLicenseDto drivingLicenseDto = mapper.map(userEntity.getDrivingLicense(), DrivingLicenseDto.class);
		GuestDetailsResponseDto guestDetailsResponseDto = mapper.map(userEntity, GuestDetailsResponseDto.class);
		guestDetailsResponseDto.setDrivingLicenseDto(drivingLicenseDto);
		return Optional.of(guestDetailsResponseDto);
	}
}

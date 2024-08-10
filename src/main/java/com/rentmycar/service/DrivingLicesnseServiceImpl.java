package com.rentmycar.service;

import javax.validation.ConstraintViolationException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rentmycar.custom_exception.ConflictException;
import com.rentmycar.custom_exception.ResourceNotFoundException;
import com.rentmycar.dao.DrivingLicenseDao;
import com.rentmycar.dao.UserDao;
import com.rentmycar.dto.DrivingLicenseDto;
import com.rentmycar.entity.DrivingLicense;
import com.rentmycar.entity.User;

@Service
@Transactional
public class DrivingLicesnseServiceImpl implements DrivingLicesnseService {

	@Autowired
	UserDao userDao;

	@Autowired
	DrivingLicenseDao drivingLicenseDao;

	@Autowired
	ModelMapper mapper;

	// add driving license by user id
	@Override
	public DrivingLicenseDto addDrivingLicense(DrivingLicenseDto drivingLicenseDto, Long userId) {
		if (drivingLicenseDto.getIssueDate().isAfter(drivingLicenseDto.getExpirationDate())) {
			throw new ConstraintViolationException("issue date is before expiry date", null);
		}
		User pUser = userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("invalid user id"));
		if (pUser.getDrivingLicense() != null) {
			throw new ConflictException("dl is already added");
		} else if (drivingLicenseDao.existsByDrivingLicenseNo(drivingLicenseDto.getDrivingLicenseNo())) {
			throw new ConflictException("dl already exists");
		}
		DrivingLicense tDrivingLicense = mapper.map(drivingLicenseDto, DrivingLicense.class);
		tDrivingLicense.setId(null);
		pUser.addDrivingLicense(tDrivingLicense);
		DrivingLicense pDrivingLicense = drivingLicenseDao.save(tDrivingLicense);
		return mapper.map(pDrivingLicense, DrivingLicenseDto.class);
	}
	
	//method to update driving license
	@Override
	public DrivingLicenseDto updateDrivingLicense(DrivingLicenseDto drivingLicenseDto, Long userId) {
		if (drivingLicenseDto.getIssueDate().isAfter(drivingLicenseDto.getExpirationDate())) {
			throw new ConstraintViolationException("issue date is before expiry date", null);
		}
		User pUser = userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("invalid user id"));
		if (pUser.getDrivingLicense() == null)
			throw new ConflictException("dl does not exist !");
		DrivingLicense dl = pUser.getDrivingLicense();
		mapper.map(drivingLicenseDto, dl);
		DrivingLicense dl1 = drivingLicenseDao.save(dl);
		return mapper.map(dl1, DrivingLicenseDto.class);
	}

}

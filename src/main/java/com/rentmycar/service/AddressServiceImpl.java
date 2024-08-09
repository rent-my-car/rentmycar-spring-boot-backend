package com.rentmycar.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rentmycar.custom_exception.ResourceNotFoundException;
import com.rentmycar.dao.AddressDao;
import com.rentmycar.dao.UserDao;
import com.rentmycar.dto.AddressDto;
import com.rentmycar.dto.AddressResDto;
import com.rentmycar.entity.Address;
import com.rentmycar.entity.User;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

	@Autowired
	UserDao userDao;

	@Autowired
	ModelMapper mapper;

	@Autowired
	AddressDao addressDao;

	// add address by user id
	@Override
	public Optional<AddressResDto> addAddress(AddressDto addressDto, Long userId) {
		User pUser = userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
		Address tAddress = mapper.map(addressDto, Address.class);
		tAddress.setIsDeleted(false);
		tAddress.setId(null);
		pUser.addAddress(tAddress);
		Address pAddress = addressDao.save(tAddress);
		AddressDto addressDto2 = mapper.map(pAddress, AddressDto.class);
		return Optional.of(new AddressResDto(userId, addressDto2));

	}

}

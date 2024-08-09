package com.rentmycar.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

	// get address by address id
	@Override
	public Optional<AddressDto> getAddressByAddressId(Long id) {
		Address pAddress = addressDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("invalid adress id"));

		return Optional.of(mapper.map(pAddress, AddressDto.class));
	}

	// get address list by user id
	@Override
	public Optional<List<AddressDto>> getAddressListbyUSerId(Long userId) {
		User pUser = userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("invalid user id"));

		// // Map List<Addresss> to List<AdressDto> using Streams and ModelMapper
		List<AddressDto> addressDtoList = pUser.getAddressList().stream()
				.map(address -> mapper.map(address, AddressDto.class)).collect(Collectors.toList());
		return Optional.of(addressDtoList);
	}
}

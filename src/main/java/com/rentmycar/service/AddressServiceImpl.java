package com.rentmycar.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rentmycar.custom_exception.ResourceNotFoundException;
import com.rentmycar.dao.AddressDao;
import com.rentmycar.dao.CarListingDao;
import com.rentmycar.dao.UserDao;
import com.rentmycar.dto.AddressDto;
import com.rentmycar.dto.AddressResDto;
import com.rentmycar.dto.CarListingDto;
import com.rentmycar.dto.DeleteAddressResDto;
import com.rentmycar.entity.Address;
import com.rentmycar.entity.CarListing;
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

	@Autowired
	CarListingDao carListingDao;

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

	// update address by address id
	@Override
	public Optional<AddressDto> updateAddressbyAddressId(AddressDto addressDto, Long addressId) {
		// making sure that AddressDto has same id as persistent address
		if (addressDto.getId().equals(addressId)) {
			Address pAddress = addressDao.findById(addressId)
					.orElseThrow(() -> new ResourceNotFoundException("invalid address id"));
			mapper.map(addressDto, pAddress);
			Address updataedAddress = addressDao.save(pAddress);
			return Optional.of(mapper.map(updataedAddress, AddressDto.class));

		}
		throw new ConstraintViolationException("address id mismatch", null);

	}

	// soft delete address by address id
	@Override
	public Optional<DeleteAddressResDto> deleteAddressByAddressId(Long addressId) {
		Address pAddress = addressDao.findById(addressId)
				.orElseThrow(() -> new ResourceNotFoundException("invalid adress id"));
		List<CarListing> carListingList = carListingDao.findByAddress(pAddress);
		// no car is listed in particular address
		if (carListingList.isEmpty()) {
			// change isDelted = true in address
			pAddress.setIsDeleted(true);
			Address deletedAddress = addressDao.save(pAddress);
			return Optional.of(mapper.map(deletedAddress, DeleteAddressResDto.class));
		}
		// one or more cars are lsisted at particualr address

		// changing the is deleted = true for listings
		carListingList.forEach((carListing -> {
			carListing.setIsDeleted(true);
		}));

		List<CarListing> dCarListingList = carListingList.stream().map(carListing -> carListingDao.save(carListing))
				.collect(Collectors.toList());

		// mapping list of CarListing to list of CarListingDto
		List<CarListingDto> carListingDtoList = dCarListingList.stream()
				.map(carListing -> mapper.map(carListing, CarListingDto.class)).collect(Collectors.toList());

		pAddress.setIsDeleted(true);
		Address deletedAddress = addressDao.save(pAddress);
		DeleteAddressResDto deleteAddressResDto = mapper.map(deletedAddress, DeleteAddressResDto.class);
		deleteAddressResDto.setCarListingDtoList(carListingDtoList);
		return Optional.of(deleteAddressResDto);
	}
	
	// get distinct cities from car_listing_address
		@Override
		public Optional<List<String>> getdistinctCityNames() {
			List<String> cityList = addressDao.getDistinctCityNames();
			return Optional.of(cityList);
		}

}

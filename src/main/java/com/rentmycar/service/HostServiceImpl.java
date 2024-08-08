package com.rentmycar.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentmycar.custom_exception.ResourceNotFoundException;
import com.rentmycar.dao.UserDao;
import com.rentmycar.dto.GetAllUsersDto;
import com.rentmycar.entity.User;
import com.rentmycar.entity.UserRoleEnum;

@Service
@Transactional
public class HostServiceImpl implements HostService {

	@Autowired
	ModelMapper mapper;

	@Autowired
	UserDao userDao;

	@Override
	public List<GetAllUsersDto> getAllHosts() {
		List<User> guestList = userDao.findByRoleEnum(UserRoleEnum.HOST)
				.orElseThrow(() -> new ResourceNotFoundException("Guest List is Empty!!"));
		TypeToken<List<GetAllUsersDto>> getallGuestDtoToken = new TypeToken<>() {
		};
		return (mapper.map(guestList, getallGuestDtoToken.getType()));

	}
}

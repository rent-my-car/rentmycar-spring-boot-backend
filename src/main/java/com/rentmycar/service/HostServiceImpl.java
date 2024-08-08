package com.rentmycar.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentmycar.custom_exception.PasswordMismatchException;
import com.rentmycar.custom_exception.ResourceNotFoundException;
import com.rentmycar.custom_exception.UserAlreadyExistsException;
import com.rentmycar.dao.HostDao;
import com.rentmycar.dao.UserDao;
import com.rentmycar.dto.GetAllUsersDto;
import com.rentmycar.dto.RegisterHostRequestDto;
import com.rentmycar.dto.RegisterHostResponseDto;
import com.rentmycar.entity.User;
import com.rentmycar.entity.UserRoleEnum;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Service
@Transactional
public class HostServiceImpl implements HostService {

	@Autowired
	ModelMapper mapper;

	@Autowired
	UserDao userDao;
	
	@Autowired
	HostDao hostdao;

//********************************************************************************************
	// register host api
	@Override
	public Optional<RegisterHostResponseDto> registerHost(
			@RequestBody @Valid RegisterHostRequestDto registerHostRequestDto) {
		if (registerHostRequestDto.getPassword().equals(registerHostRequestDto.getConfirmPassword())) {
			if (userDao.existsByMobileAndEmailAndRoleEnum(registerHostRequestDto.getMobile(),
					registerHostRequestDto.getEmail(), registerHostRequestDto.getRoleEnum())) {
				throw new UserAlreadyExistsException("user with given email and mobile already registered");
			} else if (userDao.existsByEmailAndRoleEnum(registerHostRequestDto.getEmail(),
					registerHostRequestDto.getRoleEnum())) {
				throw new UserAlreadyExistsException("user with given email already registered");
			} else if (userDao.existsByMobileAndRoleEnum(registerHostRequestDto.getMobile(),
					registerHostRequestDto.getRoleEnum())) {
				throw new UserAlreadyExistsException("user with given mobile already registered");
			} else {

				User tHost = mapper.map(registerHostRequestDto, User.class);
				tHost.setIsDeleted(false);
				User pHost = userDao.save(tHost);
				return Optional.of(mapper.map(pHost, RegisterHostResponseDto.class));

			}

		}
		throw new PasswordMismatchException("password doesn't match");
	}

	@Override
	public List<GetAllUsersDto> getAllHost() {
		List<User> HostList = hostdao.findByRoleEnum(UserRoleEnum.HOST).orElseThrow(()->new ResourceNotFoundException("Host list is empty!"));
		TypeToken<List<GetAllUsersDto>> getAllHostDtoToken = new TypeToken<>() {};
		return(mapper.map(HostList, getAllHostDtoToken.getType()));
	}

//***********************************************************************************************

}



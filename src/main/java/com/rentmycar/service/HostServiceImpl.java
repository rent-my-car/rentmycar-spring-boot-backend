package com.rentmycar.service;


import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentmycar.dao.UserDao;

@Service
@Transactional
public class HostServiceImpl implements HostService {

	@Autowired
	ModelMapper mapper;

	@Autowired
	UserDao userDao;
	
	@Autowired
	HostDao hostdao;
}



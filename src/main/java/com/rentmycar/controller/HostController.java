package com.rentmycar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.rentmycar.service.HostService;



@RestController("/host")
public class HostController {

	@Autowired
	HostService hostService;

	

}

package com.rentmycar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Getter
@Setter
@ToString
public class Address extends BaseEntity{
	
	@Column(length = 20,nullable = false)
	private String country;  	// varchar(20),not null
	
	@Column(length = 20,nullable = false)
	private String state;  	// varchar(20),not null
	
	@Column(length = 20,nullable = false)
	private String city;  	// varchar(20),not null
	
	@Column(nullable = false,columnDefinition="char(10)")
	private String pincode;  	// char(10),not null
	
	@Column(length = 30,nullable = false)
	private String area; 	// varchar(30),not null
	
	@Column(nullable = false,columnDefinition="char(10)")
	private String houseNo; 	// char(10),not null
}

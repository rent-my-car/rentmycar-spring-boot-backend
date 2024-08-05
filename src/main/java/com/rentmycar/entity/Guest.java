package com.rentmycar.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
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
public class Guest extends BaseEntity{

	@Column( length = 25, nullable = false) 
	private String firstName;
	
	@Column( length = 25, nullable = false) 
	private String lastName;
	
	@Column( length = 12, nullable = false) 
	private String mobile;
	
	@Column(length = 20, unique = true,nullable = false) 
	private String email;
	
	@Column(length = 70, nullable = false) 
	private String password;
	
	@Column(columnDefinition = "boolean default false",nullable=false)
	private Boolean isDeleted;
	
	@Embedded
	private DrivingLicense license;

}

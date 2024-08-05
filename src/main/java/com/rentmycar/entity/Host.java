package com.rentmycar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "host")
@Entity
public class Host extends BaseEntity {
	
	@Column(length = 20, nullable = false)
	String firstName; 	// VARCHAR(20),NOTNULL
	
	@Column(length = 20, nullable = false)
	String lastName;  	// VARCHAR(20),NOT NULL
	
	@Column(length = 12, nullable = false, unique = true)
	String mobile;  	// VARCHAR(12),UNIQUE NOT NULL
	
	@Column(length = 12, nullable = false, unique = true)
	String email;  	// VARCHAR(25),UNIQUE NOT NULL
	
	@Column(length = 70, nullable = false)
	String password;  	// VARCHAR(70), NOT NULL
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	Boolean isDeleted; 	// Boolean,NOT NULL,default-false
}

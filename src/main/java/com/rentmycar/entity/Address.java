package com.rentmycar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Getter
@Setter
@ToString

public class Address extends BaseEntity {

	@Column(name = "adr_line1", length = 200, nullable = false)
	private String adrLine1; // varchar(100) not null

	@Column(name = "adr_line2", length = 200)
	private String adrLine2; // varchar(100)

	@Column(nullable = false, columnDefinition = "char(10)")
	private String pincode; // char(10),not null

	@Column(length = 20, nullable = false)
	private String city; // varchar(20),not null
	
	@Column(length = 20, nullable = false)
	private String state; // varchar(20),not null

	@Column(length = 20, nullable = false)
	private String country; // varchar(20),not null

	@Column(columnDefinition = "boolean default false", nullable = false)
	private Boolean isDeleted;

//***************************************************************************************************
	// many -> Address * <----> 1 User
	@ManyToOne(fetch = FetchType.LAZY) // mandatory
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

//***************************************************************************************************

}

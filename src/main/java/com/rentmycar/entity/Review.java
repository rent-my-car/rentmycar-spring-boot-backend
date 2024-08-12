package com.rentmycar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "review")
@Data
public class Review extends BaseEntity{
	@Column(nullable = false)
	private byte rating; // tinyint,not-null

	@Column(length = 50)
	private String reviewText; // varchar(50)
	

// ****************************************************************************
	// Review * <-----------> 1 Guest
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "guest_id", nullable = false)
	private User guest;
	
// ***************************************************************************
	
	// Review * -----------> 1 CarHostAddressPricing
	@ManyToOne
	@JoinColumn(nullable = false)
	private CarListing carHostAddressPricing;
	
//****************************************************************************
	
}

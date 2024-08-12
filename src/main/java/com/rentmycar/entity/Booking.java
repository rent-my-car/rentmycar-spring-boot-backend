package com.rentmycar.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table
public class Booking extends BaseEntity {
	@Column(nullable = false)
	LocalDateTime pickUp; // DateTime,not null

	@Column(nullable = false)
	LocalDateTime dropOff; // DateTime,not null

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	@ColumnDefault("'PENDING'")
	BookingStatusEnum bookingStatusEnum; // not null,default - PENDING

	@Column(nullable = false)
	Double amount; // double,not null

	LocalDateTime paymentDateTime; // DateTime

	@Column(columnDefinition = "char(20)")
	String transactionId; // char (#### 20.

//*****************************************************************************************************
	
	// Booking * <---> 1 Guest
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "guest_id", nullable = false)
	private User guest;

//****************************************************************************************************
	
	// Booking * -------> 1 Address
	@ManyToOne
	@JoinColumn(nullable = false)
	private Address guestAddress;

//****************************************************************************************************
	
	// Booking * <-----------> 1 CarListing
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private CarListing carListing;
	
//****************************************************************************************************
}

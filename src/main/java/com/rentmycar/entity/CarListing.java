package com.rentmycar.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
public class CarListing extends BaseEntity {
	private int kmDriven; // int,not null

	@Column(columnDefinition = "TINYINT")
	private int fuelMeter; // tinyint(1-10),not null

	@Column(length = 20)
	private String carImage; // varchar(20),

	@Column
	private int noOfTrips;

	@Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
	private Boolean isAvailable; // Boolean,default-true

	@Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
	private Boolean isDeleted; // Boolean,default-false

	@Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
	private Boolean isApproved; // Boolean,default-false

	@Column(length = 20, nullable = false)
	private String registrationNo; // varchar(20),not null

	@Column(columnDefinition = "TINYINT NOT NULL")
	private int spareTyreCount; // tinyint,not null

//**************************************************************************

	// CarListing * ---------> 1 Car
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Car car;

//**************************************************************************

	// CarListing * -------> 1 CarPricing
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private CarPricing carPricing;;

//**************************************************************************

	// CarListing * --------> 1 Address
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Address address;

//**************************************************************************

	// CarListing * <------------> 1 Host
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private User host;

//***************************************************************************

	// CarListing 1 <-------------> * Booking
	@OneToMany(mappedBy = "carListing", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Booking> bookingList = new ArrayList<Booking>();

	public void addBooking(Booking booking) {
		bookingList.add(booking);
		booking.setCarListing(this);
	}

//***************************************************************************

}

package com.rentmycar.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	int kmDriven; // int,not null

	@Column(columnDefinition = "TINYINT")
	int fuelMeter; // tinyint(1-10),not null

	@Column(length = 20)
	String carImage; // varchar(20),

	@Column
	int noOfTrips;

	@Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
	Boolean isAvailable; // Boolean,default-true

	@Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
	Boolean isDeleted; // Booleane,default-false

	@Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
	Boolean isApproved; // Boolean,default-false

	@Column(length = 20, nullable = false)
	String registrationNo; // varchar(20),not null

	@Column(columnDefinition = "TINYINT NOT NULL")
	int spareTyreCount; // tinyint,not null

//**************************************************************************

	// CarListing * ---------> 1 Car
	@ManyToOne
	@JoinColumn(nullable = false)
	private Car car;

//**************************************************************************

	// CarListing * -------> 1 CarPricing
	@ManyToOne
	@JoinColumn(nullable = false)
	private CarPricing carPricing;;

//**************************************************************************

	// CarListing * --------> 1 Address
	@ManyToOne
	@JoinColumn(nullable = false)
	private Address address;

//**************************************************************************

	// CarListing * <------------> 1 Host
	@ManyToOne
	@JoinColumn(nullable = false)
	private User host;

//***************************************************************************

	// CarListing 1 <-------------> * Booking
	@OneToMany(mappedBy = "carHostAddressPricing", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Booking> bookingList = new ArrayList<Booking>();

	public void addBooking(Booking booking) {
		bookingList.add(booking);
		booking.setCarHostAddressPricing(this);
	}

//***************************************************************************

}

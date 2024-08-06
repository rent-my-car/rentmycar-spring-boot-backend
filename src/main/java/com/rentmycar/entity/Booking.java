package com.rentmycar.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

import lombok.*;

@Getter
@Setter
@ToString
@Entity
@Table
public class Booking extends BaseEntity{
	@Column(nullable = false)
	LocalDateTime pickUp; 	// DateTime,not null
	
	@Column(nullable = false)
	LocalDateTime dropOff; 	// DateTime,not null
	
	@Enumerated(EnumType.STRING)
    @Column(nullable = false,length=10)
    @ColumnDefault("'PENDING'")
	BookingStatusEnum bookingStatusEnum; 	// not null,default - PENDING
	
	@Column(nullable = false)
	Double amount; 	// double,not null
	
	@Column(nullable = false)
	LocalDateTime paymentDateTime; 	// DateTime
	
	@Column(nullable = false,length = 20,columnDefinition = "char")
	String transactionId; 	// char (#### 20.
	
}

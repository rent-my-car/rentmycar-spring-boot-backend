package com.rentmycar.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

	@MappedSuperclass
	@Getter
	@Setter
	@ToString

public class BaseEntity {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		// creation date
		@Column(updatable = false)
		@CreationTimestamp // adds creation date / time / time stamp for the entity
		private LocalDate createdOn;
		
		//updation date
		@UpdateTimestamp // add last updated - date / time / time stamp for the entity
		private LocalDateTime updatedOn;

}

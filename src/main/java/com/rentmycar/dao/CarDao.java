package com.rentmycar.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentmycar.entity.Car;
import com.rentmycar.entity.FuelTypeEnum;
import com.rentmycar.entity.TransmisssionTypeEnum;

public interface CarDao extends JpaRepository<Car, Long> {

	Optional<Car> findByBrandAndModelAndFuelTypeEnumAndSeatingCapacityAndTransmissionTypeEnum(String brand,
			String model, FuelTypeEnum fuelTypeEnum, int seatingCapacity, TransmisssionTypeEnum transmissionTypeEnum);
}

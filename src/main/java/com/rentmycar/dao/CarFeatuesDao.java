package com.rentmycar.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentmycar.entity.CarFeatures;

public interface CarFeatuesDao extends JpaRepository<CarFeatures, Long> {

	public Optional<CarFeatures> findByHasUsbChargerAndHasBluetoothAndHasPowerSteeringAndHasAirBagsAndHasAbsAndHasAc(
			Boolean hasUsbCharger, Boolean hasBluetooth, Boolean hasPowerSteering, Boolean hasAirBags, Boolean hasAbs,
			Boolean hasAc);
}

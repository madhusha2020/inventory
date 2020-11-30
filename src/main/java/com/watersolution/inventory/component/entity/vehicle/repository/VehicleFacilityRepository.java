package com.watersolution.inventory.component.entity.vehicle.repository;

import com.watersolution.inventory.component.entity.vehicle.model.db.VehicleFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleFacilityRepository extends JpaRepository<VehicleFacility, Long> {
}

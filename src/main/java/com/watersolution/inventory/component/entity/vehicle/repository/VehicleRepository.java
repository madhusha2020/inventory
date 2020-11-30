package com.watersolution.inventory.component.entity.vehicle.repository;

import com.watersolution.inventory.component.entity.vehicle.model.db.VehicleVechileFacilityId;
import com.watersolution.inventory.component.entity.vehicle.model.db.VehicleVehicleFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleVehicleFacility, VehicleVechileFacilityId> {

}

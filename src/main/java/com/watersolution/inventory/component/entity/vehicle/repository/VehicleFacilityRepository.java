package com.watersolution.inventory.component.entity.vehicle.repository;

import com.watersolution.inventory.component.entity.vehicle.model.db.VehicleFacility;
import com.watersolution.inventory.component.entity.vehicle.model.db.VehicleVechileFacilityId;
import com.watersolution.inventory.component.entity.vehicle.model.db.VehicleVehicleFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleFacilityRepository extends JpaRepository<VehicleVehicleFacility, VehicleVechileFacilityId> {

}

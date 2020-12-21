package com.watersolution.inventory.component.entity.vehicle.repository;

import com.watersolution.inventory.component.entity.vehicle.model.db.VehicleFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilityRepository extends JpaRepository<VehicleFacility, Long> {

    List<VehicleFacility> findAllByStatusIn(List<Integer> statusList);

    VehicleFacility findByIdAndStatusIn(long id, List<Integer> statusList);
}

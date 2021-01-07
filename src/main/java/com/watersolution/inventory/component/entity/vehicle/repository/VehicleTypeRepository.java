package com.watersolution.inventory.component.entity.vehicle.repository;

import com.watersolution.inventory.component.entity.vehicle.model.db.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long> {

    List<VehicleType> findAllByStatusIn(List<Integer> statusList);

    List<VehicleType> findAllByStatus(int status);
}

package com.watersolution.inventory.component.entity.vehicle.repository;

import com.watersolution.inventory.component.entity.vehicle.model.db.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findAllByStatusIn(List<Integer> statusList);

    List<Vehicle> findAllByStatus(int status);

    Vehicle findByIdAndStatusIn(long id, List<Integer> statusList);
}

package com.watersolution.inventory.component.management.delivery.repository;

import com.watersolution.inventory.component.management.delivery.model.db.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    List<Delivery> findAllByStatusIn(List<Integer> statusList);
}

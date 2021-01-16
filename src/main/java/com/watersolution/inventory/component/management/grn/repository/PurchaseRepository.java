package com.watersolution.inventory.component.management.grn.repository;

import com.watersolution.inventory.component.management.grn.model.db.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}

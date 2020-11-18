package com.watersolution.inventory.component.management.inventory.repository;

import com.watersolution.inventory.component.management.inventory.model.db.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Inventory findByIdAndStatus(long id, int status);
}

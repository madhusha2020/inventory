package com.watersolution.inventory.component.management.product.disposal.repository;

import com.watersolution.inventory.component.management.product.disposal.model.db.DisposalInventory;
import com.watersolution.inventory.component.management.product.disposal.model.db.DisposalInventoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisposalInventoryRepository extends JpaRepository<DisposalInventory, DisposalInventoryId> {

}

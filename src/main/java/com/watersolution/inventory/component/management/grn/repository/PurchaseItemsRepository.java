package com.watersolution.inventory.component.management.grn.repository;

import com.watersolution.inventory.component.management.grn.model.db.PurchaseItemId;
import com.watersolution.inventory.component.management.grn.model.db.PurchaseItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseItemsRepository extends JpaRepository<PurchaseItems, PurchaseItemId> {

}

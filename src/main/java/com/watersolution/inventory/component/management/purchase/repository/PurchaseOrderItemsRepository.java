package com.watersolution.inventory.component.management.purchase.repository;

import com.watersolution.inventory.component.management.purchase.model.db.PurchaseOrderItemId;
import com.watersolution.inventory.component.management.purchase.model.db.PurchaseOrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderItemsRepository extends JpaRepository<PurchaseOrderItems, PurchaseOrderItemId> {
}

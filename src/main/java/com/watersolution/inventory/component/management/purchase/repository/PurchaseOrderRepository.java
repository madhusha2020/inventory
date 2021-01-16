package com.watersolution.inventory.component.management.purchase.repository;

import com.watersolution.inventory.component.management.purchase.model.db.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    PurchaseOrder findById(long purchaseOrderId);

    PurchaseOrder findByIdAndStatusIn(long purchaseOrderId, List<Integer> statusList);

    List<PurchaseOrder> findAllByStatusIn(List<Integer> statusList);

    List<PurchaseOrder> findAllByStatus(int status);

    List<PurchaseOrder> findBySupplierIdAndStatus(long supplierId, int status);

    List<PurchaseOrder> findBySupplierIdAndStatusIn(long supplierId, List<Integer> statusList);
}

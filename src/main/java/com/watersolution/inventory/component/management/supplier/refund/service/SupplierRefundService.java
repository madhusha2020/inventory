package com.watersolution.inventory.component.management.supplier.refund.service;

import com.watersolution.inventory.component.management.supplier.refund.model.db.SupplierRefundInventory;

import java.util.List;

public interface SupplierRefundService {
    List<SupplierRefundInventory> saveAllSupplierRefundInventories(List<SupplierRefundInventory> supplierRefundInventoryList);
}

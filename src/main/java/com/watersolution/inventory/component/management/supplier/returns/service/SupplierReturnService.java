package com.watersolution.inventory.component.management.supplier.returns.service;

import com.watersolution.inventory.component.management.supplier.returns.model.db.SupplierReturnInventory;

import java.util.List;

public interface SupplierReturnService {
    List<SupplierReturnInventory> saveAllSupplierReturnInventories(List<SupplierReturnInventory> supplierReturnInventoryList);
}

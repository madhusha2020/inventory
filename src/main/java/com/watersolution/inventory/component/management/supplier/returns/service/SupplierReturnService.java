package com.watersolution.inventory.component.management.supplier.returns.service;

import com.watersolution.inventory.component.management.grn.model.db.Purchase;
import com.watersolution.inventory.component.management.supplier.returns.model.api.SupplierReturnList;
import com.watersolution.inventory.component.management.supplier.returns.model.db.SupplierReturn;
import com.watersolution.inventory.component.management.supplier.returns.model.db.SupplierReturnInventory;

import java.util.List;

public interface SupplierReturnService {
    List<SupplierReturnInventory> saveAllSupplierReturnInventories(List<SupplierReturnInventory> supplierReturnInventoryList, Purchase purchase);

    SupplierReturnList getAllSupplierReturns();

    SupplierReturn getSupplierReturnById(String supplierReturnId);

    SupplierReturn updateSupplierReturn(SupplierReturn supplierReturn);
}

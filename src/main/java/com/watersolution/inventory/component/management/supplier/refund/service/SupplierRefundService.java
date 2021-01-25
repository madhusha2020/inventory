package com.watersolution.inventory.component.management.supplier.refund.service;

import com.watersolution.inventory.component.management.grn.model.db.Purchase;
import com.watersolution.inventory.component.management.supplier.refund.model.api.SupplierRefundList;
import com.watersolution.inventory.component.management.supplier.refund.model.db.SupplierRefund;
import com.watersolution.inventory.component.management.supplier.refund.model.db.SupplierRefundInventory;

import java.util.List;

public interface SupplierRefundService {
    List<SupplierRefundInventory> saveAllSupplierRefundInventories(List<SupplierRefundInventory> supplierRefundInventoryList, Purchase purchase);

    SupplierRefundList getAllSupplierRefunds();

    SupplierRefund getSupplierRefundById(String supplierRefundId);

    SupplierRefund updateSupplierRefund(SupplierRefund supplierRefund);
}

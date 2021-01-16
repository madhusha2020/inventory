package com.watersolution.inventory.component.management.grn.service;

import com.watersolution.inventory.component.management.grn.model.db.Purchase;
import com.watersolution.inventory.component.management.purchase.model.api.PurchaseOrderItemsList;

public interface PurchaseService {

    Purchase savePurchase(PurchaseOrderItemsList purchaseOrderItemsList);
}

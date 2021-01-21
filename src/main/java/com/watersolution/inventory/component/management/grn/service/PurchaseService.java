package com.watersolution.inventory.component.management.grn.service;

import com.watersolution.inventory.component.management.grn.model.api.PurchaseItemsList;
import com.watersolution.inventory.component.management.grn.model.api.PurchaseList;
import com.watersolution.inventory.component.management.grn.model.db.Purchase;
import com.watersolution.inventory.component.management.purchase.model.api.PurchaseOrderItemsList;

public interface PurchaseService {

    PurchaseList getAllPurchases();

    Purchase getPurchaseById(String purchaseId);

    PurchaseItemsList updatePurchase(PurchaseItemsList purchaseItemsList);

    Purchase savePurchase(PurchaseOrderItemsList purchaseOrderItemsList);
}

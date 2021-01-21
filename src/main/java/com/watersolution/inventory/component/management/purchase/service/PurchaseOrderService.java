package com.watersolution.inventory.component.management.purchase.service;

import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.management.purchase.model.api.PurchaseOrderItemsList;
import com.watersolution.inventory.component.management.purchase.model.api.PurchaseOrderList;
import com.watersolution.inventory.component.management.purchase.model.db.PurchaseOrder;

public interface PurchaseOrderService {

    PurchaseOrderList getAllPurchaseOrders();

    PurchaseOrderList getOrdersBySupplier(TransactionRequest transactionRequest);

    PurchaseOrderItemsList placePurchaseOrder(PurchaseOrderItemsList purchaseOrderItemsList);

    PurchaseOrder approvePurchaseOrder(TransactionRequest transactionRequest);

    PurchaseOrder rejectPurchaseOrder(TransactionRequest transactionRequest);

    PurchaseOrder getPurchaseOrderById(String purchaseOrderId);

    PurchaseOrder updatePurchaseOrder(PurchaseOrder purchaseOrder);
}

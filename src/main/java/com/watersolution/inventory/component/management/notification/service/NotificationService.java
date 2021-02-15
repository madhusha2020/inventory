package com.watersolution.inventory.component.management.notification.service;

import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.management.complain.model.db.Complain;
import com.watersolution.inventory.component.management.delivery.model.db.Delivery;
import com.watersolution.inventory.component.management.inventory.model.db.Inventory;
import com.watersolution.inventory.component.management.notification.model.api.NotificationList;
import com.watersolution.inventory.component.management.order.model.db.Order;
import com.watersolution.inventory.component.management.purchase.model.db.PurchaseOrder;

public interface NotificationService {

    NotificationList getNotificationsByUser(TransactionRequest transactionRequest);

    NotificationList getNewNotificationsByUser(TransactionRequest transactionRequest);

    void orderNotification(Order order);

    void purchaseOrderNotification(PurchaseOrder purchaseOrder);

    void inventoryNotification(Inventory inventory);

    void disposalInventoryNotification(Inventory inventory);

    void deliveryNotification(Delivery delivery);

    void deliverySuspendNotification(Delivery delivery);

    void supplierNotification(PurchaseOrder purchaseOrder);

    void complainNotification(Complain complain);
}

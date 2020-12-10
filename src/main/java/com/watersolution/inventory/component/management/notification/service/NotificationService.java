package com.watersolution.inventory.component.management.notification.service;

import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.management.delivery.model.db.Delivery;
import com.watersolution.inventory.component.management.inventory.model.db.Inventory;
import com.watersolution.inventory.component.management.notification.model.api.NotificationList;

public interface NotificationService {

    NotificationList getNotificationsByUser(TransactionRequest transactionRequest);

    NotificationList getNewNotificationsByUser(TransactionRequest transactionRequest);

    void inventoryNotification(Inventory inventory);

    void deliveryNotification(Delivery delivery);
}

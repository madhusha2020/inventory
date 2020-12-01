package com.watersolution.inventory.component.management.notification.service;

import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.management.inventory.model.db.Inventory;
import com.watersolution.inventory.component.management.notification.model.api.NotificationList;

public interface NotificationService {

    NotificationList getNotificationsByUser(TransactionRequest transactionRequest);

    NotificationList getAwaitingNotificationsByUser(TransactionRequest transactionRequest);

    NotificationList getNewNotificationsByUser(TransactionRequest transactionRequest);

    void inventoryNotification(Inventory inventory);
}

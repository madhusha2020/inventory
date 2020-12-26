package com.watersolution.inventory.component.management.delivery.service;

import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.management.delivery.model.api.DeliveryList;
import com.watersolution.inventory.component.management.delivery.model.db.Delivery;
import com.watersolution.inventory.component.management.order.model.db.Order;

public interface DeliveryService {

    DeliveryList getAllDeliveries();

    Delivery getDeliveryById(String deliveryId);

    Delivery saveDelivery(Order order);

    Delivery updateDelivery(Delivery delivery);

    Delivery suspendDelivery(TransactionRequest transactionRequest);
}

package com.watersolution.inventory.component.management.inventory.service;

import com.watersolution.inventory.component.management.order.model.db.OrderItems;

import java.util.List;

public interface InventoryService {

    void pendingOrderUpdateInventory(List<OrderItems> orderItems);

    void rejectedOrderUpdateInventory(List<OrderItems> orderItems);
}

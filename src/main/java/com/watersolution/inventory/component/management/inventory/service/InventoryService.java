package com.watersolution.inventory.component.management.inventory.service;

import com.watersolution.inventory.component.management.order.model.db.OrderItems;

import java.util.List;

public interface InventoryService {

    void updateInventory(List<OrderItems> orderItems);
}

package com.watersolution.inventory.component.management.inventory.service;

import com.watersolution.inventory.component.management.inventory.model.api.InventoryList;
import com.watersolution.inventory.component.management.inventory.model.db.Inventory;
import com.watersolution.inventory.component.management.order.model.db.OrderItems;

import java.util.List;

public interface InventoryService {

    Inventory getByItemId(Long itemId);

    void preOrderValidate(List<OrderItems> orderItems);

    void pendingOrderUpdateInventory(List<OrderItems> orderItems);

    void rejectedOrderUpdateInventory(List<OrderItems> orderItems);

    InventoryList getAllItems();
}

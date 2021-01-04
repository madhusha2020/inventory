package com.watersolution.inventory.component.management.inventory.service;

import com.watersolution.inventory.component.management.inventory.model.api.InventoryList;
import com.watersolution.inventory.component.management.inventory.model.db.Inventory;
import com.watersolution.inventory.component.management.order.model.db.OrderItems;
import com.watersolution.inventory.component.management.product.disposal.model.db.DisposalInventory;

import java.util.List;

public interface InventoryService {

    InventoryList getAllItems();

    Inventory getByItemId(Long itemId);

    void preOrderValidate(List<OrderItems> orderItems);

    void pendingOrderUpdateInventory(List<OrderItems> orderItems);

    void rejectedOrderUpdateInventory(List<OrderItems> orderItems);

    void preDisposalValidate(List<DisposalInventory> disposalInventories);

    void disposalUpdateInventory(List<DisposalInventory> disposalInventories);
}

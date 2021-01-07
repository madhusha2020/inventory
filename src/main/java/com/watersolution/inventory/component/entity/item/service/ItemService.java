package com.watersolution.inventory.component.entity.item.service;

import com.watersolution.inventory.component.common.model.api.PageDetails;
import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.entity.item.model.api.ItemList;
import com.watersolution.inventory.component.entity.item.model.db.Item;
import com.watersolution.inventory.component.management.inventory.model.api.InventoryItem;

public interface ItemService {

    ItemList getAllItems(PageDetails pageDetails);

    ItemList getAllActiveItems(PageDetails pageDetails);

    Item getItemById(String itemId);

    ItemList searchItems(PageDetails pageDetails);

    InventoryItem saveInventoryItem(InventoryItem inventoryItem);

    InventoryItem updateInventoryItem(InventoryItem inventoryItem);

    Item saveItem(Item item);

    Item suspendItem(TransactionRequest transactionRequest);

    Item activateItem(TransactionRequest transactionRequest);
}

package com.watersolution.inventory.component.entity.item.service;

import com.watersolution.inventory.component.common.model.api.PageDetails;
import com.watersolution.inventory.component.entity.item.model.api.ItemList;
import com.watersolution.inventory.component.entity.item.model.db.Item;
import com.watersolution.inventory.component.management.inventory.model.api.InventoryItem;

public interface ItemService {

    ItemList getAllItems(PageDetails pageDetails);

    ItemList getAllActiveItems(PageDetails pageDetails);

    ItemList searchItems(PageDetails pageDetails);

    InventoryItem saveItem(InventoryItem inventoryItem);
}

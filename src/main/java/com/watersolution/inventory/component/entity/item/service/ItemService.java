package com.watersolution.inventory.component.entity.item.service;

import com.watersolution.inventory.component.common.model.api.PageDetails;
import com.watersolution.inventory.component.entity.item.model.api.ItemList;
import com.watersolution.inventory.component.entity.item.model.db.Item;

public interface ItemService {

    ItemList getAllItems(PageDetails pageDetails);

    ItemList searchItems(PageDetails pageDetails);

    Item saveItem(Item item);
}

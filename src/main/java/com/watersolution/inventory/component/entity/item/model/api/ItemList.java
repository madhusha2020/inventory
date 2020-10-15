package com.watersolution.inventory.component.entity.item.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.entity.item.model.db.Item;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ItemList extends ResponseDefault {
    private List<Item> itemList;
    private int totalPages;

    public ItemList(List<Item> itemList, int totalPages) {
        this.itemList = itemList;
        this.totalPages = totalPages;
    }
}

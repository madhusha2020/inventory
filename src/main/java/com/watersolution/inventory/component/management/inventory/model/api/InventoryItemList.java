package com.watersolution.inventory.component.management.inventory.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class InventoryItemList extends ResponseDefault {
    private List<InventoryItem> inventoryItemList;

    public InventoryItemList(List<InventoryItem> inventoryItemList) {
        this.inventoryItemList = inventoryItemList;
    }
}

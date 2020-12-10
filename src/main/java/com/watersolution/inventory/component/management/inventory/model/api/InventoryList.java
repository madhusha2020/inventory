package com.watersolution.inventory.component.management.inventory.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.management.inventory.model.db.Inventory;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class InventoryList extends ResponseDefault {

    private List<Inventory> inventoryList;

    public InventoryList(List<Inventory> inventoryList) {
        this.inventoryList = inventoryList;
    }
}

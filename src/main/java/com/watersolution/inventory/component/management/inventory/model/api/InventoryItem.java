package com.watersolution.inventory.component.management.inventory.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.entity.item.model.db.Item;
import com.watersolution.inventory.component.management.inventory.model.db.Inventory;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;

@Data
@EqualsAndHashCode(callSuper = false)
public class InventoryItem extends ResponseDefault {
    @Valid
    private Item item;
    @Valid
    private Inventory inventory;

    public InventoryItem(@Valid Item item, @Valid Inventory inventory) {
        this.item = item;
        this.inventory = inventory;
    }
}

package com.watersolution.inventory.component.management.sales.model.db;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class SaleInventoryId implements Serializable {

    @Column(name = "sale_id")
    private long saleId;

    @Column(name = "inventory_id")
    private long inventoryId;

    public SaleInventoryId() {
    }

    public SaleInventoryId(long saleId, long inventoryId) {
        this.saleId = saleId;
        this.inventoryId = inventoryId;
    }
}

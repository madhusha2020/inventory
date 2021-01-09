package com.watersolution.inventory.component.management.supplier.returns.model.db;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class SupplierReplaceInventoryId implements Serializable {

    @Column(name = "supplier_id")
    private long supplierId;

    @Column(name = "inventory_id")
    private long inventoryId;

    public SupplierReplaceInventoryId() {
    }

    public SupplierReplaceInventoryId(long supplierId, long inventoryId) {
        this.supplierId = supplierId;
        this.inventoryId = inventoryId;
    }
}

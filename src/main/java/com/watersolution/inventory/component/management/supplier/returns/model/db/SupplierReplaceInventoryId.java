package com.watersolution.inventory.component.management.supplier.returns.model.db;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class SupplierReplaceInventoryId implements Serializable {

    @Column(name = "supplierreturn_id")
    private long supplierReturnId;

    @Column(name = "inventory_id")
    private long inventoryId;

    public SupplierReplaceInventoryId() {
    }

    public SupplierReplaceInventoryId(long supplierReturnId, long inventoryId) {
        this.supplierReturnId = supplierReturnId;
        this.inventoryId = inventoryId;
    }
}

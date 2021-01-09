package com.watersolution.inventory.component.management.supplier.refund.model.db;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class SupplierRefundInventoryId implements Serializable {

    @Column(name = "supplierrefund_id")
    private long supplierRefundId;

    @Column(name = "inventory_id")
    private long inventoryId;

    public SupplierRefundInventoryId() {
    }

    public SupplierRefundInventoryId(long supplierRefundId, long inventoryId) {
        this.supplierRefundId = supplierRefundId;
        this.inventoryId = inventoryId;
    }
}

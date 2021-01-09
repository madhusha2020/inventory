package com.watersolution.inventory.component.management.purchase.model.db;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class PurchaseOrderItemId implements Serializable {

    @Column(name = "purchaseorder_id")
    private long purchaseOrderId;

    @Column(name = "item_id")
    private long itemId;

    public PurchaseOrderItemId() {
    }

    public PurchaseOrderItemId(long purchaseOrderId, long itemId) {
        this.purchaseOrderId = purchaseOrderId;
        this.itemId = itemId;
    }
}

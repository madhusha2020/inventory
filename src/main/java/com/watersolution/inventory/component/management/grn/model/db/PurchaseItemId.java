package com.watersolution.inventory.component.management.grn.model.db;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class PurchaseItemId implements Serializable {

    @Column(name = "purchase_id")
    private long purchaseId;

    @Column(name = "item_id")
    private long itemId;

    public PurchaseItemId() {
    }

    public PurchaseItemId(long purchaseId, long itemId) {
        this.purchaseId = purchaseId;
        this.itemId = itemId;
    }
}

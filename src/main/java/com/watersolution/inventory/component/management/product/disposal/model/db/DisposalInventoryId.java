package com.watersolution.inventory.component.management.product.disposal.model.db;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class DisposalInventoryId implements Serializable {

    @Column(name = "disposal_id")
    private long disposalId;

    @Column(name = "inventory_id")
    private long inventoryId;

    public DisposalInventoryId() {
    }

    public DisposalInventoryId(long disposalId, long inventoryId) {
        this.disposalId = disposalId;
        this.inventoryId = inventoryId;
    }
}

package com.watersolution.inventory.component.management.product.inbound.model.db;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class ProductInboundItemId implements Serializable {

    @Column(name = "productintbound_id")
    private long produInboundId;

    @Column(name = "item_id")
    private long itemId;

    public ProductInboundItemId() {

    }

    public ProductInboundItemId(long produInboundId, long itemId) {
        this.produInboundId = produInboundId;
        this.itemId = itemId;
    }
}

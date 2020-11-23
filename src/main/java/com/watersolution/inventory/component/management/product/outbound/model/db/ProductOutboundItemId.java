package com.watersolution.inventory.component.management.product.outbound.model.db;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class ProductOutboundItemId implements Serializable {

    @Column(name = "productoutbound_id")
    private long productOutboundId;

    @Column(name = "item_id")
    private long itemId;

    public ProductOutboundItemId() {

    }

    public ProductOutboundItemId(long productOutboundId, long itemId) {
        this.productOutboundId = productOutboundId;
        this.itemId = itemId;
    }
}

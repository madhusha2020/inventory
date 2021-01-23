package com.watersolution.inventory.component.management.product.inbound.model.db;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class ProductInboundItemId implements Serializable {

    @Column(name = "productinbound_id")
    private long productInboundId;

    @Column(name = "item_id")
    private long itemId;

    public ProductInboundItemId() {

    }

    public ProductInboundItemId(long productInboundId, long itemId) {
        this.productInboundId = productInboundId;
        this.itemId = itemId;
    }
}

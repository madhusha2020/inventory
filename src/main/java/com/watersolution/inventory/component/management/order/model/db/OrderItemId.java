package com.watersolution.inventory.component.management.order.model.db;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class OrderItemId implements Serializable {

    @Column(name = "order_id")
    private long orderId;

    @Column(name = "item_id")
    private long itemId;

    public OrderItemId() {
    }

    public OrderItemId(long orderId, long itemId) {
        this.orderId = orderId;
        this.itemId = itemId;
    }
}

package com.watersolution.inventory.component.management.order.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.entity.item.model.db.Item;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customerorderitem")
@EqualsAndHashCode(callSuper = false)
public class OrderItems extends Auditable {

    @EmbeddedId
    private OrderItemId orderItemId;

    @JsonBackReference(value = "order")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @JsonBackReference(value = "item")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "qty")
    private Integer qty;

    public OrderItems() {
    }
}

package com.watersolution.inventory.component.management.purchase.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.entity.item.model.db.Item;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "purchaseorderitem")
@EqualsAndHashCode(callSuper = false)
public class PurchaseOrderItems extends Auditable {

    @EmbeddedId
    private PurchaseOrderItemId purchaseOrderItemId;

    @JsonBackReference(value = "purchaseorder")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("purchaseOrderId")
    @JoinColumn(name = "purchaseorder_id")
    private PurchaseOrder purchaseOrder;

    @JsonBackReference(value = "purchaseorderitem")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "qty")
    private Integer qty;

    public PurchaseOrderItems() {

    }
}

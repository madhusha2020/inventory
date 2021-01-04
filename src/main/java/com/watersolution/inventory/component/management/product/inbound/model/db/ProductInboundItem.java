package com.watersolution.inventory.component.management.product.inbound.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.entity.item.model.db.Item;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "productinbounditem")
@EqualsAndHashCode(callSuper = false)
public class ProductInboundItem extends Auditable {

    @EmbeddedId
    private ProductInboundItemId productInboundItemId;

    @JsonBackReference(value = "productInbound")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productInboundId")
    @JoinColumn(name = "productinbound_id")
    private ProductInbound productInbound;

    @JsonBackReference(value = "productInboundItem")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "doexpire")
    private LocalDate doexpire;

    public ProductInboundItem() {
    }
}

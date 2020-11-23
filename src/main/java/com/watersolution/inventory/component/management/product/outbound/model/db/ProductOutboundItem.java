package com.watersolution.inventory.component.management.product.outbound.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.entity.item.model.db.Item;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "productoutbounditem")
@EqualsAndHashCode(callSuper = false)
public class ProductOutboundItem extends Auditable {

    @EmbeddedId
    private ProductOutboundItemId productOutboundItemId;

    @JsonBackReference(value = "productOutbound")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productOutboundId")
    @JoinColumn(name = "productoutbound_id")
    private ProductOutbound productOutbound;

    @JsonBackReference(value = "productOutboundItem")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "doexpire")
    private LocalDate doexpire;

    public ProductOutboundItem() {
    }
}

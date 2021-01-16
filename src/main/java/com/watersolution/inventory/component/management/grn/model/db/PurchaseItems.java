package com.watersolution.inventory.component.management.grn.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.entity.item.model.db.Item;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "purchaseitem")
@EqualsAndHashCode(callSuper = false)
public class PurchaseItems extends Auditable {

    @EmbeddedId
    private PurchaseItemId purchaseItemId;

    @JsonBackReference(value = "purchase")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("purchaseId")
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @JsonBackReference(value = "purchaseitem")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "unitprice")
    private Double unitprice;

    @Column(name = "doexpire")
    private LocalDate doexpire;

    public PurchaseItems() {

    }
}

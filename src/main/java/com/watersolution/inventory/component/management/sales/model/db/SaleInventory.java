package com.watersolution.inventory.component.management.sales.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.management.inventory.model.db.Inventory;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "saleinventory")
@EqualsAndHashCode(callSuper = false)
public class SaleInventory extends Auditable {

    @EmbeddedId
    private SaleInventoryId saleInventoryId;

    @JsonBackReference(value = "saleinventory")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("saleId")
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @JsonBackReference(value = "inventorysale")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("inventoryId")
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "unitprice")
    private Double unitprice;

    public SaleInventory() {
    }
}

package com.watersolution.inventory.component.management.supplier.returns.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.management.inventory.model.db.Inventory;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "supplierreturninventory")
@EqualsAndHashCode(callSuper = false)
public class SupplierReturnInventory extends Auditable {

    @EmbeddedId
    private SupplierReturnInventoryId supplierReturnInventoryId;

    @JsonBackReference(value = "supplierreturn")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("supplierReturnId")
    @JoinColumn(name = "supplierreturn_id")
    private SupplierReturn supplierReturn;

    @JsonBackReference(value = "supplierreturninventory")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("inventoryId")
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @Column(name = "qty")
    private Integer qty;

    @Transient
    private long itemId;

    public SupplierReturnInventory() {

    }
}

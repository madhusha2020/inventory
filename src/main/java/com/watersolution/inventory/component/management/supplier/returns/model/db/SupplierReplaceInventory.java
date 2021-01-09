package com.watersolution.inventory.component.management.supplier.returns.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.management.inventory.model.db.Inventory;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "supplierreplaceinventory")
@EqualsAndHashCode(callSuper = false)
public class SupplierReplaceInventory extends Auditable {

    @EmbeddedId
    private SupplierReplaceInventoryId supplierReplaceInventoryId;

    @JsonBackReference(value = "supplierreplace")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("supplierReturnId")
    @JoinColumn(name = "supplierreturn_id")
    private SupplierReturn supplierReturn;

    @JsonBackReference(value = "supplierreplaceinventory")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("inventoryId")
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "doexpire")
    private LocalDate doexpire;

    public SupplierReplaceInventory() {

    }
}

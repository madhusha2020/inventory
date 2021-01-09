package com.watersolution.inventory.component.management.supplier.returns.model.db;

import com.watersolution.inventory.component.common.model.db.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "supplierreplace")
@EqualsAndHashCode(callSuper = false)
public class SupplierReplaceInventory extends Auditable {

    @EmbeddedId
    private SupplierReplaceInventoryId supplierReplaceInventoryId;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "doexpire")
    private LocalDate doexpire;

    public SupplierReplaceInventory() {

    }
}

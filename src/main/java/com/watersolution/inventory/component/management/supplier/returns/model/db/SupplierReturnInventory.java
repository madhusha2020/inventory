package com.watersolution.inventory.component.management.supplier.returns.model.db;

import com.watersolution.inventory.component.common.model.db.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "supplierreturn")
@EqualsAndHashCode(callSuper = false)
public class SupplierReturnInventory extends Auditable {

    @EmbeddedId
    private SupplierReturnInventoryId supplierReturnInventoryId;

    @Column(name = "qty")
    private Integer qty;

    public SupplierReturnInventory() {

    }
}

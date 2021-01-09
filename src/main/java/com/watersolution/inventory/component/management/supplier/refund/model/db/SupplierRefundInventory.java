package com.watersolution.inventory.component.management.supplier.refund.model.db;

import com.watersolution.inventory.component.common.model.db.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "supplierrefund")
@EqualsAndHashCode(callSuper = false)
public class SupplierRefundInventory extends Auditable {

    @EmbeddedId
    private SupplierRefundInventoryId supplierRefundInventoryId;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "unitprice")
    private BigDecimal unitprice;

    public SupplierRefundInventory() {

    }
}

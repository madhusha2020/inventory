package com.watersolution.inventory.component.management.supplier.refund.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.management.inventory.model.db.Inventory;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "supplierrefundinventory")
@EqualsAndHashCode(callSuper = false)
public class SupplierRefundInventory extends Auditable {

    @EmbeddedId
    private SupplierRefundInventoryId supplierRefundInventoryId;

    @JsonBackReference(value = "supplierrefund")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("supplierRefundId")
    @JoinColumn(name = "supplierrefund_id")
    private SupplierRefund supplierRefund;

    @JsonBackReference(value = "supplierrefundinventory")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("inventoryId")
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "unitprice")
    private BigDecimal unitprice;

    public SupplierRefundInventory() {

    }
}

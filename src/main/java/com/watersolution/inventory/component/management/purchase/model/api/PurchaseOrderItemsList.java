package com.watersolution.inventory.component.management.purchase.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.management.payment.supplier.model.db.SupplierPayment;
import com.watersolution.inventory.component.management.purchase.model.db.PurchaseOrder;
import com.watersolution.inventory.component.management.purchase.model.db.PurchaseOrderItems;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseOrderItemsList extends ResponseDefault {

    @Valid
    private PurchaseOrder purchaseOrder;
    @Valid
    private List<PurchaseOrderItems> purchaseOrderItems;
    @Valid
    private SupplierPayment supplierPayment;

    private String userId;

    public PurchaseOrderItemsList(@Valid PurchaseOrder purchaseOrder, @Valid List<PurchaseOrderItems> purchaseOrderItems) {
        this.purchaseOrder = purchaseOrder;
        this.purchaseOrderItems = purchaseOrderItems;
    }
}

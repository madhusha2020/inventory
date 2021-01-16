package com.watersolution.inventory.component.management.purchase.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.management.purchase.model.db.PurchaseOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseOrderList extends ResponseDefault {

    @Valid
    private List<PurchaseOrder> purchaseOrders;

    public PurchaseOrderList(List<PurchaseOrder> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }
}

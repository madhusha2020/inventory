package com.watersolution.inventory.component.management.grn.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.management.grn.model.db.Purchase;
import com.watersolution.inventory.component.management.grn.model.db.PurchaseItems;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseItemsList extends ResponseDefault {

    @Valid
    private Purchase purchase;
    @Valid
    private List<PurchaseItems> purchaseItemsList;

    public PurchaseItemsList(@Valid Purchase purchase, @Valid List<PurchaseItems> purchaseItemsList) {
        this.purchase = purchase;
        this.purchaseItemsList = purchaseItemsList;
    }
}

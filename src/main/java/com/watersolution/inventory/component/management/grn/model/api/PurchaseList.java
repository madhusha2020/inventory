package com.watersolution.inventory.component.management.grn.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.management.grn.model.db.Purchase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseList extends ResponseDefault {

    @Valid
    private List<Purchase> purchaseList;

    public PurchaseList(@Valid List<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
    }
}

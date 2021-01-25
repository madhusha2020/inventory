package com.watersolution.inventory.component.management.supplier.refund.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.management.supplier.refund.model.db.SupplierRefund;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class SupplierRefundList extends ResponseDefault {
    @Valid List<SupplierRefund> supplierRefunds;

    public SupplierRefundList(@Valid List<SupplierRefund> supplierRefunds) {
        this.supplierRefunds = supplierRefunds;
    }
}

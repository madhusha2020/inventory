package com.watersolution.inventory.component.management.payment.supplier.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.management.payment.supplier.model.db.SupplierPayment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class SupplierPaymentList extends ResponseDefault {

    @Valid
    private List<SupplierPayment> supplierPaymentList;

    public SupplierPaymentList(@Valid List<SupplierPayment> supplierPaymentList) {
        this.supplierPaymentList = supplierPaymentList;
    }
}

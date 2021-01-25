package com.watersolution.inventory.component.management.supplier.returns.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.management.supplier.returns.model.db.SupplierReturn;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class SupplierReturnList extends ResponseDefault {
    @Valid List<SupplierReturn> supplierReturnList;

    public SupplierReturnList(@Valid List<SupplierReturn> supplierReturnList) {
        this.supplierReturnList = supplierReturnList;
    }
}

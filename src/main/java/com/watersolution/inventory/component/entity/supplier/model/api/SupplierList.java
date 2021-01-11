package com.watersolution.inventory.component.entity.supplier.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.entity.supplier.model.db.Supplier;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class SupplierList extends ResponseDefault {

    @Valid private List<Supplier> supplierList;

    public SupplierList(@Valid List<Supplier> supplierList) {
        this.supplierList = supplierList;
    }
}

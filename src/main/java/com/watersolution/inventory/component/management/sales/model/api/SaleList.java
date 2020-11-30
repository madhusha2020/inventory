package com.watersolution.inventory.component.management.sales.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.management.sales.model.db.Sale;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class SaleList extends ResponseDefault {

    @Valid
    private List<Sale> saleList;

    public SaleList(List<Sale> saleList) {
        this.saleList = saleList;
    }
}

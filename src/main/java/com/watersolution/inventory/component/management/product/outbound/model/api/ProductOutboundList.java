package com.watersolution.inventory.component.management.product.outbound.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.management.product.outbound.model.db.ProductOutbound;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductOutboundList extends ResponseDefault {
    @Valid
    private List<ProductOutbound> productOutboundList;

    public ProductOutboundList(List<ProductOutbound> productOutboundList) {
        this.productOutboundList = productOutboundList;
    }
}

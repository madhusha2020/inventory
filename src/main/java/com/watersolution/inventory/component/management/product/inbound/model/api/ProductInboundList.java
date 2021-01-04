package com.watersolution.inventory.component.management.product.inbound.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.management.product.inbound.model.db.ProductInbound;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductInboundList extends ResponseDefault {
    @Valid
    private List<ProductInbound> productInboundList;

    public ProductInboundList(List<ProductInbound> productInboundList) {
        this.productInboundList = productInboundList;
    }
}

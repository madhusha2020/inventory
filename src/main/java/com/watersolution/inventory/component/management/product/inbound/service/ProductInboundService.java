package com.watersolution.inventory.component.management.product.inbound.service;

import com.watersolution.inventory.component.management.grn.model.db.Purchase;
import com.watersolution.inventory.component.management.product.inbound.model.api.ProductInboundList;
import com.watersolution.inventory.component.management.product.inbound.model.db.ProductInbound;

public interface ProductInboundService {

    ProductInboundList getAllProductInbounds();

    ProductInbound getProductInboundById(String productInboundById);

    void updateProductInbound(Purchase purchase);
}

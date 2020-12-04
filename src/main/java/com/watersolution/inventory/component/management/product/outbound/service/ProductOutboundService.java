package com.watersolution.inventory.component.management.product.outbound.service;

import com.watersolution.inventory.component.management.order.model.db.Order;
import com.watersolution.inventory.component.management.product.outbound.model.api.ProductOutboundList;
import com.watersolution.inventory.component.management.product.outbound.model.db.ProductOutbound;

public interface ProductOutboundService {

    ProductOutboundList getAllProductOutbounds();

    ProductOutbound getProductOutboundById(String productOutboundById);

    void updateProductOutbound(Order order);
}

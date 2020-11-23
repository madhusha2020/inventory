package com.watersolution.inventory.component.management.product.outbound.service;

import com.watersolution.inventory.component.management.order.model.db.Order;
import com.watersolution.inventory.component.management.product.outbound.model.api.ProductOutboundList;

public interface ProductOutboundService {

    ProductOutboundList getAllProductOutbounds();

    void updateProductOutbound(Order order);
}

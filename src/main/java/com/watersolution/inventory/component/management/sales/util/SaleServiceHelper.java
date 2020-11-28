package com.watersolution.inventory.component.management.sales.util;

import com.watersolution.inventory.component.management.order.model.api.OrderItemsList;
import com.watersolution.inventory.component.management.sales.model.db.Sale;

public interface SaleServiceHelper {
    Sale calculateSales(Sale sale, OrderItemsList orderItemsList);
}

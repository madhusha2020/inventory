package com.watersolution.inventory.component.management.sales.service;

import com.watersolution.inventory.component.management.order.model.api.OrderItemsList;
import com.watersolution.inventory.component.management.sales.model.db.Sale;

public interface SaleService {

    Sale saveSale(OrderItemsList orderItemsLists);

    Sale getSaleById(String saleId);
}

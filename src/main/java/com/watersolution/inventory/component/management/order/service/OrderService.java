package com.watersolution.inventory.component.management.order.service;

import com.watersolution.inventory.component.management.order.model.db.Order;
import com.watersolution.inventory.component.management.order.model.api.OrderItemsList;
import com.watersolution.inventory.component.management.order.model.api.OrderList;

public interface OrderService {

    OrderItemsList placeOrder(OrderItemsList orderItemsList);

    OrderList getOrdersByCustomer(long customerId);

    Order getOrderById(long orderId);
}

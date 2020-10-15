package com.watersolution.inventory.component.management.order.service;

import com.watersolution.inventory.component.management.order.model.Order;
import com.watersolution.inventory.component.management.order.model.OrderItemsList;
import com.watersolution.inventory.component.management.order.model.OrderList;

public interface OrderService {

    OrderItemsList placeOrder(OrderItemsList orderItemsList);

    OrderList getOrdersByCustomer(long customerId);

    Order getOrderById(long orderId);
}

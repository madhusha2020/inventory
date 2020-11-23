package com.watersolution.inventory.component.management.order.service;

import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.management.order.model.db.Order;
import com.watersolution.inventory.component.management.order.model.api.OrderItemsList;
import com.watersolution.inventory.component.management.order.model.api.OrderList;

public interface OrderService {

    OrderItemsList placeOrder(OrderItemsList orderItemsList);

    Order approveOrder(TransactionRequest transactionRequest);

    Order rejectOrder(TransactionRequest transactionRequest);

    OrderList getAllOrders();

    OrderList getAllActiveOrders();

    OrderList getOrdersByCustomer(TransactionRequest transactionRequest);

    OrderList getActiveOrdersByCustomer(TransactionRequest transactionRequest);

    Order getOrderById(String orderId);
}

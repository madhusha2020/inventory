package com.watersolution.inventory.component.management.payment.customer.service;

import com.watersolution.inventory.component.management.order.model.api.OrderItemsList;
import com.watersolution.inventory.component.management.payment.customer.model.db.CustomerPayment;

public interface CustomerPaymentService {

    CustomerPayment savePayment(OrderItemsList orderItemsList);
}

package com.watersolution.inventory.component.management.payment.customer.service;

import com.watersolution.inventory.component.management.order.model.api.OrderItemsList;
import com.watersolution.inventory.component.management.payment.customer.model.api.CustomerPaymentList;
import com.watersolution.inventory.component.management.payment.customer.model.db.CustomerPayment;

public interface CustomerPaymentService {

    CustomerPaymentList getAllCustomerPayments();

    CustomerPayment savePayment(OrderItemsList orderItemsList);
}

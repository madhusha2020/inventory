package com.watersolution.inventory.component.management.payment.supplier.service;

import com.watersolution.inventory.component.management.payment.supplier.model.api.SupplierPaymentList;
import com.watersolution.inventory.component.management.payment.supplier.model.db.SupplierPayment;

public interface SupplierPaymentService {

    SupplierPaymentList getAllSupplierPayments();

    SupplierPayment getSupplierPaymentById(String paymentId);
}

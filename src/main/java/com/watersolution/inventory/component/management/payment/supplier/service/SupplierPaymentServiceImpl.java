package com.watersolution.inventory.component.management.payment.supplier.service;

import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.common.validator.CustomValidator;
import com.watersolution.inventory.component.management.payment.supplier.model.api.SupplierPaymentList;
import com.watersolution.inventory.component.management.payment.supplier.model.db.SupplierPayment;
import com.watersolution.inventory.component.management.payment.supplier.repository.SupplierPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SupplierPaymentServiceImpl implements SupplierPaymentService {

    @Autowired
    private CustomValidator customValidator;
    @Autowired
    private SupplierPaymentRepository supplierPaymentRepository;

    @Override
    public SupplierPaymentList getAllSupplierPayments() {
        return new SupplierPaymentList(supplierPaymentRepository.findAllByStatusIn(Status.getAllStatusAsList()).stream().map(this::mapSupplerPaymentDetails).collect(Collectors.toList()));
    }

    @Override
    public SupplierPayment getSupplierPaymentById(String paymentId) {
        customValidator.validateNullOrEmpty(paymentId, "payment Id");
        SupplierPayment supplierPayment = supplierPaymentRepository.findByIdAndStatusIn(Long.valueOf(paymentId), Status.getAllStatusAsList());
        customValidator.validateFoundNull(supplierPayment, "supplier Payment");
        return mapSupplerPaymentDetails(supplierPayment);
    }

    private SupplierPayment mapSupplerPaymentDetails(SupplierPayment supplierPayment) {
        return supplierPayment;
    }
}

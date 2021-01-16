package com.watersolution.inventory.component.management.payment.supplier.service;

import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.common.validator.CustomValidator;
import com.watersolution.inventory.component.management.payment.supplier.model.api.SupplierPaymentList;
import com.watersolution.inventory.component.management.payment.supplier.model.db.SupplierPayment;
import com.watersolution.inventory.component.management.payment.supplier.repository.SupplierPaymentRepository;
import com.watersolution.inventory.component.management.purchase.model.api.PurchaseOrderItemsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
public class SupplierPaymentServiceImpl implements SupplierPaymentService {

    @Autowired
    private SupplierPaymentRepository supplierPaymentRepository;
    @Autowired
    private CustomValidator customValidator;

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

    @Transactional
    @Override
    public SupplierPayment savePayment(PurchaseOrderItemsList purchaseOrderItemsList) {
        
        purchaseOrderItemsList.getSupplierPayment().setCode("");
        purchaseOrderItemsList.getSupplierPayment().setDescription("");
        purchaseOrderItemsList.getSupplierPayment().setDate(LocalDate.now());
        purchaseOrderItemsList.getSupplierPayment().setRef("INV" + (int) (Math.random() * 125) + 1);
        purchaseOrderItemsList.getSupplierPayment().setStatus(Status.PENDING.getValue());
        purchaseOrderItemsList.getSupplierPayment().fillCompulsory(purchaseOrderItemsList.getUserId());
        return mapSupplerPaymentDetails(supplierPaymentRepository.save(purchaseOrderItemsList.getSupplierPayment()));
    }

    @Override
    public SupplierPayment updatePayment(SupplierPayment supplierPayment) {
        supplierPayment.fillUpdateCompulsory(supplierPayment.getUserId());
        return mapSupplerPaymentDetails(supplierPaymentRepository.save(supplierPayment));
    }

    @Override
    public SupplierPayment approvePayment(SupplierPayment supplierPayment) {
        supplierPayment.setStatus(Status.ACTIVE.getValue());
        supplierPayment.fillUpdateCompulsory(supplierPayment.getUserId());
        return mapSupplerPaymentDetails(supplierPaymentRepository.save(supplierPayment));
    }

    @Override
    public SupplierPayment rejectPayment(SupplierPayment supplierPayment) {
        supplierPayment.setStatus(Status.REJECTED.getValue());
        supplierPayment.fillUpdateCompulsory(supplierPayment.getUserId());
        return mapSupplerPaymentDetails(supplierPaymentRepository.save(supplierPayment));
    }

    private SupplierPayment mapSupplerPaymentDetails(SupplierPayment supplierPayment) {
        return supplierPayment;
    }
}

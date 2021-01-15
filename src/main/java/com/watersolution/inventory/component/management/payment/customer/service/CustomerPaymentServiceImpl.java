package com.watersolution.inventory.component.management.payment.customer.service;

import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.common.validator.CustomValidator;
import com.watersolution.inventory.component.management.order.model.api.OrderItemsList;
import com.watersolution.inventory.component.management.payment.customer.model.api.CustomerPaymentList;
import com.watersolution.inventory.component.management.payment.customer.model.db.CustomerPayment;
import com.watersolution.inventory.component.management.payment.customer.repository.CustomerPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
public class CustomerPaymentServiceImpl implements CustomerPaymentService {

    @Autowired
    private CustomValidator customValidator;
    @Autowired
    private CustomerPaymentRepository customerPaymentRepository;

    @Override
    public CustomerPaymentList getAllCustomerPayments() {
        return new CustomerPaymentList(customerPaymentRepository.findAllByStatusIn(Status.getAllStatusAsList()).stream().map(this::mapCustomerPaymentDetails).collect(Collectors.toList()));
    }

    @Override
    public CustomerPayment getCustomerPaymentById(String paymentId) {
        customValidator.validateNullOrEmpty(paymentId, "payment Id");
        CustomerPayment customerPayment = customerPaymentRepository.findByIdAndStatusIn(Long.valueOf(paymentId), Status.getAllStatusAsList());
        customValidator.validateFoundNull(customerPayment, "customer Payment");
        return mapCustomerPaymentDetails(customerPayment);
    }

    @Transactional
    @Override
    public CustomerPayment savePayment(OrderItemsList orderItemsList) {

        CustomerPayment customerPayment = new CustomerPayment();
        customerPayment.setCode("");
        customerPayment.setDescription("");
        customerPayment.setDate(LocalDate.now());
        customerPayment.setAmount(orderItemsList.getSale().getTotal());
        customerPayment.setPaymenttype(orderItemsList.getPaymentType());
        customerPayment.setRef("INV" + (int) (Math.random() * 125) + 1);
        customerPayment.setStatus(Status.ACTIVE.getValue());
        customerPayment.fillCompulsory(orderItemsList.getUserId());
        customerPayment.setSale(orderItemsList.getSale());
        customerPayment.setChemicalTest(orderItemsList.getChemicalTest());
        return customerPaymentRepository.save(customerPayment);
    }

    private CustomerPayment mapCustomerPaymentDetails(CustomerPayment customerPayment) {
        return customerPayment;
    }
}

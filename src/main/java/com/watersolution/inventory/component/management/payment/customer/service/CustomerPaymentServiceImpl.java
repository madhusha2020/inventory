package com.watersolution.inventory.component.management.payment.customer.service;

import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.management.order.model.api.OrderItemsList;
import com.watersolution.inventory.component.management.payment.customer.model.db.CustomerPayment;
import com.watersolution.inventory.component.management.payment.customer.repository.CustomerPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
public class CustomerPaymentServiceImpl implements CustomerPaymentService {

    @Autowired
    private CustomerPaymentRepository customerPaymentRepository;

    @Transactional
    @Override
    public CustomerPayment savePayment(OrderItemsList orderItemsList) {

        CustomerPayment customerPayment = new CustomerPayment();
        customerPayment.setCode("");
        customerPayment.setDescription("");
        customerPayment.setDate(LocalDate.now());
        customerPayment.setAmount(orderItemsList.getTotal());
        customerPayment.setPaymenttype(orderItemsList.getPaymentType());
        customerPayment.setRef("INV"+(int) (Math.random() * 125) + 1);
        customerPayment.setStatus(Status.ACTIVE.getValue());
        customerPayment.fillCompulsory(orderItemsList.getUserId());
        customerPayment.setSale(orderItemsList.getSale());
        customerPayment.setChemicalTest(orderItemsList.getChemicalTest());
        return customerPaymentRepository.save(customerPayment);
    }
}

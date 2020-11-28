package com.watersolution.inventory.component.management.payment.customer.repository;

import com.watersolution.inventory.component.management.payment.customer.model.db.CustomerPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerPaymentRepository extends JpaRepository<CustomerPayment, Long> {

}
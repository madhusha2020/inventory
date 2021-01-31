package com.watersolution.inventory.component.management.payment.customer.repository;

import com.watersolution.inventory.component.management.payment.customer.model.db.CustomerPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CustomerPaymentRepository extends JpaRepository<CustomerPayment, Long> {

    List<CustomerPayment> findAllByStatusIn(List<Integer> statusList);

    CustomerPayment findByIdAndStatusIn(long id, List<Integer> statusList);

    List<CustomerPayment> findAllByStatusInAndDateBetween(List<Integer> statusList, LocalDate fromDate, LocalDate toDate);

    List<CustomerPayment> findBySale_Customer_IdAndStatusInAndDateBetween(long id, List<Integer> statusList, LocalDate fromDate, LocalDate toDate);
}

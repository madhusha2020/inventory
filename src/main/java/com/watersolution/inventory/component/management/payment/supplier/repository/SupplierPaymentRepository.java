package com.watersolution.inventory.component.management.payment.supplier.repository;

import com.watersolution.inventory.component.management.payment.supplier.model.db.SupplierPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierPaymentRepository extends JpaRepository<SupplierPayment, Long> {

    List<SupplierPayment> findAllByStatusIn(List<Integer> statusList);
}

package com.watersolution.inventory.component.management.payment.supplier.repository;

import com.watersolution.inventory.component.management.payment.supplier.model.db.SupplierPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SupplierPaymentRepository extends JpaRepository<SupplierPayment, Long> {

    List<SupplierPayment> findAllByStatusIn(List<Integer> statusList);

    SupplierPayment findByIdAndStatusIn(long id, List<Integer> statusList);

    List<SupplierPayment> findAllByStatusInAndDateBetween(List<Integer> statusList, LocalDate fromDate, LocalDate toDate);

    List<SupplierPayment> findByPurchase_Supplier_IdAndStatusInAndDateBetween(long id, List<Integer> statusList, LocalDate fromDate, LocalDate toDate);
}

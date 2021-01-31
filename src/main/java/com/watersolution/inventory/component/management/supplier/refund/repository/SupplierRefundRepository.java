package com.watersolution.inventory.component.management.supplier.refund.repository;

import com.watersolution.inventory.component.management.supplier.refund.model.db.SupplierRefund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SupplierRefundRepository extends JpaRepository<SupplierRefund, Long> {

    List<SupplierRefund> findAllByStatusIn(List<Integer> statusList);

    SupplierRefund findByIdAndStatusIn(long id, List<Integer> statusList);

    List<SupplierRefund> findAllByStatusInAndDateBetween(List<Integer> statusList, LocalDate fromDate, LocalDate toDate);
}

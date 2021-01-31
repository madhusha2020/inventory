package com.watersolution.inventory.component.management.grn.repository;

import com.watersolution.inventory.component.management.grn.model.db.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findAllByStatusIn(List<Integer> statusList);

    Purchase findByIdAndStatusIn(long id, List<Integer> statusList);

    Purchase findByIdAndStatus(long id, int status);

    List<Purchase> findAllByStatusInAndDateBetween(List<Integer> statusList, LocalDate fromDate, LocalDate toDate);

    List<Purchase> findBySupplier_IdAndStatusInAndDateBetween(long id, List<Integer> statusList, LocalDate fromDate, LocalDate toDate);
}

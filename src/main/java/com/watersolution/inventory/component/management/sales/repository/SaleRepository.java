package com.watersolution.inventory.component.management.sales.repository;

import com.watersolution.inventory.component.management.sales.model.db.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findAllByStatusIn(List<Integer> statusList);

    Sale findByIdAndStatusIn(long saleId, List<Integer> statusList);

    List<Sale> findAllByStatusInAndDateBetween(List<Integer> statusList, LocalDate fromDate, LocalDate toDate);

    List<Sale> findByCustomer_IdAndStatusInAndDateBetween(long id, List<Integer> statusList, LocalDate fromDate, LocalDate toDate);
}

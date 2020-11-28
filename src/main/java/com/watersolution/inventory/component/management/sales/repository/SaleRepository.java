package com.watersolution.inventory.component.management.sales.repository;

import com.watersolution.inventory.component.management.sales.model.db.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    Sale findByIdAndStatus(long saleId, int status);
}

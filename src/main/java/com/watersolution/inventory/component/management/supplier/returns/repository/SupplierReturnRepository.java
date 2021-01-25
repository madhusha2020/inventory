package com.watersolution.inventory.component.management.supplier.returns.repository;

import com.watersolution.inventory.component.management.supplier.returns.model.db.SupplierReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierReturnRepository extends JpaRepository<SupplierReturn, Long> {

    List<SupplierReturn> findAllByStatusIn(List<Integer> statusList);

    SupplierReturn findByIdAndStatusIn(long id, List<Integer> statusList);
}

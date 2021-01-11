package com.watersolution.inventory.component.entity.supplier.repository;

import com.watersolution.inventory.component.entity.supplier.model.db.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    List<Supplier> findAllByStatusIn(List<Integer> statusList);

    List<Supplier> findAllByStatus(int status);

    Supplier findByIdAndStatus(long id, int status);

    Supplier findByIdAndStatusIn(long id, List<Integer> statusList);
}

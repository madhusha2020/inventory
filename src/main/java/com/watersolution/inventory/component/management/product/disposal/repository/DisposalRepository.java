package com.watersolution.inventory.component.management.product.disposal.repository;

import com.watersolution.inventory.component.management.product.disposal.model.db.Disposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisposalRepository extends JpaRepository<Disposal, Long> {

    List<Disposal> findAllByStatusIn(List<Integer> statusList);
}

package com.watersolution.inventory.component.management.product.outbound.repository;

import com.watersolution.inventory.component.management.product.outbound.model.db.ProductOutbound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOutboundRepository extends JpaRepository<ProductOutbound, Long> {

    List<ProductOutbound> findAllByStatusIn(List<Integer> statusList);

    ProductOutbound findByIdAndStatusIn(Long id, List<Integer> statusList);
}

package com.watersolution.inventory.component.management.product.inbound.repository;

import com.watersolution.inventory.component.management.product.inbound.model.db.ProductInbound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInboundRepository extends JpaRepository<ProductInbound, Long> {

    List<ProductInbound> findAllByStatusIn(List<Integer> statusList);

    ProductInbound findByIdAndStatusIn(Long id, List<Integer> statusList);
}

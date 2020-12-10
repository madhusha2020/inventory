package com.watersolution.inventory.component.entity.discount.repository;

import com.watersolution.inventory.component.entity.discount.model.db.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    Discount findByCategoryAndTypeAndStatus(String category, String type, int status);
}

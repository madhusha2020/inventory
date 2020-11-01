package com.watersolution.inventory.component.management.order.repository;

import com.watersolution.inventory.component.management.order.model.db.OrderItemId;
import com.watersolution.inventory.component.management.order.model.db.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, OrderItemId> {

}

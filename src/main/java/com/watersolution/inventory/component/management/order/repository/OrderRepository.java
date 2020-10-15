package com.watersolution.inventory.component.management.order.repository;

import com.watersolution.inventory.component.management.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByIdAndStatus(long orderId, int status);

    List<Order> findByCustomerIdAndStatus(long customerId, int status);
}

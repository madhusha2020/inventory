package com.watersolution.inventory.component.management.order.repository;

import com.watersolution.inventory.component.management.order.model.db.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findById(long orderId);

    Order findByIdAndStatusIn(long orderId, List<Integer> statusList);

    List<Order> findAllByStatusIn(List<Integer> statusList);

    List<Order> findAllByStatus(int status);

    List<Order> findByCustomerIdAndStatus(long customerId, int status);

    List<Order> findByCustomerIdAndStatusIn(long customerId, List<Integer> statusList);

    List<Order> findAllByStatusInAndDoorderedBetween(List<Integer> statusList, LocalDate fromDate, LocalDate toDate);

    List<Order> findByCustomer_IdAndStatusInAndDoorderedBetween(long id, List<Integer> statusList, LocalDate fromDate, LocalDate toDate);
}

package com.watersolution.inventory.component.management.order.service;

import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.management.order.model.Order;
import com.watersolution.inventory.component.management.order.model.OrderItemId;
import com.watersolution.inventory.component.management.order.model.OrderItemsList;
import com.watersolution.inventory.component.management.order.model.OrderList;
import com.watersolution.inventory.component.management.order.repository.OrderItemsRepository;
import com.watersolution.inventory.component.management.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Override
    public OrderItemsList placeOrder(OrderItemsList orderItemsList) {

        orderItemsList.getOrder().fillCompulsory(orderItemsList.getUserId());
        orderItemsList.getOrder().setStatus(Status.ACTIVE.getValue());
        Order order = orderRepository.save(orderItemsList.getOrder());

        orderItemsList.getOrderItems().stream().forEach(orderItem -> {
            orderItem.setOrder(order);
            orderItem.setOrderItemId(new OrderItemId(orderItem.getOrder().getId(), orderItem.getItem().getId()));
            orderItem.fillCompulsory(orderItemsList.getUserId());
            orderItem.setStatus(Status.ACTIVE.getValue());
        });

        return new OrderItemsList(order, orderItemsRepository.saveAll(orderItemsList.getOrderItems()));
    }

    @Override
    public OrderList getOrdersByCustomer(long customerId) {
        return new OrderList(orderRepository.findByCustomerIdAndStatus(customerId, Status.ACTIVE.getValue()));
    }

    @Override
    public Order getOrderById(long orderId) {
        return orderRepository.findByIdAndStatus(orderId, Status.ACTIVE.getValue());
    }
}

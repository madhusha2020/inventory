package com.watersolution.inventory.component.management.order.service;

import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.entity.customer.service.CustomerService;
import com.watersolution.inventory.component.management.order.model.api.OrderItemsList;
import com.watersolution.inventory.component.management.order.model.api.OrderList;
import com.watersolution.inventory.component.management.order.model.db.Order;
import com.watersolution.inventory.component.management.order.model.db.OrderItemId;
import com.watersolution.inventory.component.management.order.repository.OrderItemsRepository;
import com.watersolution.inventory.component.management.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Transactional
    @Override
    public OrderItemsList placeOrder(OrderItemsList orderItemsList) {

        orderItemsList.getOrder().setCustomer(customerService.getCustomerByUserName(orderItemsList.getOrder().getCustomer().getEmail()));
        orderItemsList.getOrder().fillCompulsory(orderItemsList.getUserId());
        orderItemsList.getOrder().setStatus(Status.PENDING.getValue());
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
    public OrderList getAllOrders() {
        return new OrderList(orderRepository.findAllByStatusIn(Status.getAllStatusAsList()));
    }

    @Override
    public OrderList getAllActiveOrders() {
        return new OrderList(orderRepository.findAllByStatus(Status.ACTIVE.getValue()));
    }

    @Override
    public OrderList getOrdersByCustomer(TransactionRequest transactionRequest) {
        return new OrderList(orderRepository.findByCustomerIdAndStatusIn(transactionRequest.getId(), Status.getAllStatusAsList()));
    }

    @Override
    public OrderList getActiveOrdersByCustomer(TransactionRequest transactionRequest) {
        return new OrderList(orderRepository.findByCustomerIdAndStatus(transactionRequest.getId(), Status.ACTIVE.getValue()));
    }

    @Override
    public Order getOrderById(long orderId) {
        return orderRepository.findByIdAndStatus(orderId, Status.ACTIVE.getValue());
    }
}

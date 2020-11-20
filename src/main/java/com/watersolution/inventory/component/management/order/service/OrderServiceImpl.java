package com.watersolution.inventory.component.management.order.service;

import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.entity.customer.service.CustomerService;
import com.watersolution.inventory.component.management.inventory.service.InventoryService;
import com.watersolution.inventory.component.management.order.model.api.OrderItemsList;
import com.watersolution.inventory.component.management.order.model.api.OrderList;
import com.watersolution.inventory.component.management.order.model.db.Order;
import com.watersolution.inventory.component.management.order.model.db.OrderItemId;
import com.watersolution.inventory.component.management.order.repository.OrderItemsRepository;
import com.watersolution.inventory.component.management.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private InventoryService inventoryService;
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

        //Payment init
        inventoryService.pendingOrderUpdateInventory(orderItemsList.getOrderItems());
        OrderItemsList responseOrderItemsList = new OrderItemsList(order, orderItemsRepository.saveAll(orderItemsList.getOrderItems()));

        return responseOrderItemsList;
    }

    @Transactional
    @Override
    public Order approveOrder(TransactionRequest transactionRequest) {

        Order order = orderRepository.findById(transactionRequest.getId());
        Status.validateState("Order", order.getStatus(), Status.PENDING);
        order.setStatus(Status.ACTIVE.getValue());

        //product out-bound init
        orderRepository.save(order);
        //sales init

        return null;
    }

    @Transactional
    @Override
    public Order rejectOrder(TransactionRequest transactionRequest) {

        Order order = orderRepository.findById(transactionRequest.getId());
        Status.validateState("Order", order.getStatus(), Status.PENDING);
        order.setStatus(Status.REJECTED.getValue());

        inventoryService.rejectedOrderUpdateInventory(order.getOrderItems());
        //payment refund
        orderRepository.save(order);

        return null;
    }

    @Override
    public OrderList getAllOrders() {
        return new OrderList(orderRepository.findAllByStatusIn(Status.getAllStatusAsList())
                .stream()
                .map(this::mapOrderDetails)
                .collect(Collectors.toList()));
    }

    @Override
    public OrderList getAllActiveOrders() {
        return new OrderList(orderRepository.findAllByStatus(Status.ACTIVE.getValue())
                .stream()
                .map(this::mapOrderDetails)
                .collect(Collectors.toList()));
    }

    @Override
    public OrderList getOrdersByCustomer(TransactionRequest transactionRequest) {
        return new OrderList(orderRepository.findByCustomerIdAndStatusIn(customerService.getCustomerByUserName(transactionRequest.getEmail())
                .getId(), Status.getAllStatusAsList())
                .stream()
                .map(this::mapOrderDetails)
                .collect(Collectors.toList()));
    }

    @Override
    public OrderList getActiveOrdersByCustomer(TransactionRequest transactionRequest) {
        return new OrderList(orderRepository.findByCustomerIdAndStatus(customerService.getCustomerByUserName(transactionRequest.getEmail())
                .getId(), Status.ACTIVE.getValue())
                .stream()
                .map(this::mapOrderDetails)
                .collect(Collectors.toList()));
    }

    @Override
    public Order getOrderById(long orderId) {
        return mapOrderDetails(orderRepository.findByIdAndStatus(orderId, Status.ACTIVE.getValue()));
    }

    private Order mapOrderDetails(Order order) {
        order.setName(order.getCustomer().getName());
        order.setAddress(order.getCustomer().getAddress());
        order.setContact1(order.getCustomer().getContact1());
        order.setEmail(order.getCustomer().getEmail());
        order.setType(order.getCustomer().getType());
        return order;
    }
}

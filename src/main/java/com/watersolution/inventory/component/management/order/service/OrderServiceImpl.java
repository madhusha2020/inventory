package com.watersolution.inventory.component.management.order.service;

import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.common.validator.CustomValidator;
import com.watersolution.inventory.component.entity.customer.model.db.Customer;
import com.watersolution.inventory.component.entity.customer.service.CustomerService;
import com.watersolution.inventory.component.management.delivery.service.DeliveryService;
import com.watersolution.inventory.component.management.inventory.service.InventoryService;
import com.watersolution.inventory.component.management.order.model.api.OrderItemsList;
import com.watersolution.inventory.component.management.order.model.api.OrderList;
import com.watersolution.inventory.component.management.order.model.db.CustomerCompound;
import com.watersolution.inventory.component.management.order.model.db.Order;
import com.watersolution.inventory.component.management.order.model.db.OrderItemId;
import com.watersolution.inventory.component.management.order.repository.OrderItemsRepository;
import com.watersolution.inventory.component.management.order.repository.OrderRepository;
import com.watersolution.inventory.component.management.payment.customer.service.CustomerPaymentService;
import com.watersolution.inventory.component.management.product.outbound.service.ProductOutboundService;
import com.watersolution.inventory.component.management.sales.service.SaleService;
import com.watersolution.inventory.component.management.test.service.ChemicalTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private SaleService saleService;
    @Autowired
    private ChemicalTestService chemicalTestService;
    @Autowired
    private CustomerPaymentService customerPaymentService;
    @Autowired
    private ProductOutboundService productOutboundService;
    @Autowired
    private DeliveryService deliveryService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemsRepository orderItemsRepository;
    @Autowired
    private CustomValidator customValidator;

    @Transactional
    @Override
    public OrderItemsList preOrderValidate(OrderItemsList orderItemsList) {
        inventoryService.preOrderValidate(orderItemsList.getOrderItems());
        return orderItemsList;
    }

    @Transactional
    @Override
    public OrderItemsList placeOrder(OrderItemsList orderItemsList) {

        Customer customer = customerService.getCustomerByUserName(orderItemsList.getOrder().getCustomer().getEmail());
        customValidator.validateFoundNull(customer, "customer");

        orderItemsList.getOrder().setCustomer(customer);
        orderItemsList.getOrder().setDoordered(LocalDate.now());
        orderItemsList.getOrder().setDeliveryaddress(customer.getAddress());
        orderItemsList.getOrder().fillCompulsory(orderItemsList.getUserId());
        orderItemsList.getOrder().setStatus(Status.PENDING.getValue());

        CustomerCompound customerCompound = new CustomerCompound();
        customerCompound.setName(customer.getName());
        customerCompound.setDescription(customer.getDescription());
        customerCompound.fillCompulsory(orderItemsList.getUserId());
        customerCompound.setStatus(Status.ACTIVE.getValue());
        customerCompound.setOrder(orderItemsList.getOrder());
        orderItemsList.getOrder().setCustomerCompound(customerCompound);

        Order order = orderRepository.save(orderItemsList.getOrder());

        orderItemsList.getOrderItems().stream().forEach(orderItem -> {
            orderItem.setOrder(order);
            orderItem.setOrderItemId(new OrderItemId(orderItem.getOrder().getId(), orderItem.getItem().getId()));
            orderItem.fillCompulsory(orderItemsList.getUserId());
            orderItem.setStatus(Status.ACTIVE.getValue());
        });

        /**
         * Inventory Update
         * Order Init
         * Sale Init
         * Chemical Test Init
         * Payment Init
         */
        inventoryService.pendingOrderUpdateInventory(orderItemsList.getOrderItems());
        orderItemsRepository.saveAll(orderItemsList.getOrderItems());
        orderItemsList.setSale(saleService.saveSale(orderItemsList));
        orderItemsList.setChemicalTest(chemicalTestService.saveChemicalTest(orderItemsList));
        customerPaymentService.savePayment(orderItemsList);

        return orderItemsList;
    }

    @Transactional
    @Override
    public Order approveOrder(TransactionRequest transactionRequest) {

        Order order = orderRepository.findById(transactionRequest.getId());
        customValidator.validateFoundNull(order, "order");
        Status.validateState("Order", order.getStatus(), Status.PENDING);
        order.setStatus(Status.AWAITING.getValue());
        order.setDosold(LocalDate.now());
        order.fillUpdateCompulsory(transactionRequest.getUserId());

        Status.validateState("Sale",  order.getSale().getStatus(), Status.PENDING);
        order.getSale().setStatus(Status.ACTIVE.getValue());
        order.getSale().fillUpdateCompulsory(transactionRequest.getUserId());

        Status.validateState("Chemical Test",  order.getSale().getCustomerPayment().getChemicalTest().getStatus(), Status.PENDING);
        order.getSale().getCustomerPayment().getChemicalTest().setStatus(Status.ACTIVE.getValue());
        order.getSale().getCustomerPayment().getChemicalTest().fillUpdateCompulsory(transactionRequest.getUserId());
        order.setUserId(transactionRequest.getUserId());

        /**
         * Product Outbound Update
         * Order Update
         * Sales Update
         * Chemical Test Update
         * Delivery Init
         */
        productOutboundService.updateProductOutbound(order);
        orderRepository.save(order);
        deliveryService.saveDelivery(order);

        return mapOrderDetails(order);
    }

    @Transactional
    @Override
    public Order rejectOrder(TransactionRequest transactionRequest) {

        Order order = orderRepository.findById(transactionRequest.getId());
        customValidator.validateFoundNull(order, "order");
        Status.validateState("Order", order.getStatus(), Status.PENDING);
        order.setStatus(Status.REJECTED.getValue());
        order.fillUpdateCompulsory(transactionRequest.getUserId());

        Status.validateState("Sale",  order.getSale().getStatus(), Status.PENDING);
        order.getSale().setStatus(Status.REJECTED.getValue());
        order.getSale().fillUpdateCompulsory(transactionRequest.getUserId());

        Status.validateState("Chemical Test",  order.getSale().getCustomerPayment().getChemicalTest().getStatus(), Status.PENDING);
        order.getSale().getCustomerPayment().getChemicalTest().setStatus(Status.REJECTED.getValue());
        order.getSale().getCustomerPayment().getChemicalTest().fillUpdateCompulsory(transactionRequest.getUserId());

        /**
         * Inventory Update
         * Order Update
         * Sales Update
         * Chemical Test Update
         */
        inventoryService.rejectedOrderUpdateInventory(order.getOrderItems());
        orderRepository.save(order);

        return mapOrderDetails(order);
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
    public Order getOrderById(String orderId) {
        customValidator.validateNullOrEmpty(orderId, "orderId");
        Order order = orderRepository.findByIdAndStatus(Long.valueOf(orderId), Status.ACTIVE.getValue());
        customValidator.validateFoundNull(order, "order");
        return mapOrderDetails(order);
    }

    private Order mapOrderDetails(Order order) {
        order.getOrderItems().stream().forEach(orderItems -> {

        });
        order.setName(order.getCustomer().getName());
        order.setAddress(order.getCustomer().getAddress());
        order.setContact1(order.getCustomer().getContact1());
        order.setEmail(order.getCustomer().getEmail());
        order.setType(order.getCustomer().getType());
        return order;
    }
}

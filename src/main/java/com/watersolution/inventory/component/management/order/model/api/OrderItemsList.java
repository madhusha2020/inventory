package com.watersolution.inventory.component.management.order.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.management.order.model.db.Order;
import com.watersolution.inventory.component.management.order.model.db.OrderItems;
import com.watersolution.inventory.component.management.sales.model.db.Sale;
import com.watersolution.inventory.component.management.test.model.db.ChemicalTest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class OrderItemsList extends ResponseDefault {
    @Valid
    private Order order;
    @Valid
    private List<OrderItems> orderItems;

    private String userId;
    private String paymentType;
    private BigDecimal total;
    private BigDecimal discount;
    private Sale sale;
    private ChemicalTest chemicalTest;

    public OrderItemsList(Order order, List<OrderItems> orderItems) {
        this.order = order;
        this.orderItems = orderItems;
    }
}

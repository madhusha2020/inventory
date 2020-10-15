package com.watersolution.inventory.component.management.order.model;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class OrderItemsList extends ResponseDefault {
    private Order order;
    private List<OrderItems> orderItems;
    private String userId;

    public OrderItemsList(Order order, List<OrderItems> orderItems){
        this.order = order;
        this.orderItems = orderItems;
    }
}

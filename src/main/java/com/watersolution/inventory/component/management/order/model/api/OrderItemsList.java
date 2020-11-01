package com.watersolution.inventory.component.management.order.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.management.order.model.db.Order;
import com.watersolution.inventory.component.management.order.model.db.OrderItems;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class OrderItemsList extends ResponseDefault {
    @Valid
    private Order order;
    @Valid
    private List<OrderItems> orderItems;
    private String userId;

    public OrderItemsList(Order order, List<OrderItems> orderItems){
        this.order = order;
        this.orderItems = orderItems;
    }
}

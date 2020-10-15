package com.watersolution.inventory.component.management.order.model;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class OrderList extends ResponseDefault {
    private List<Order> orders;

    public OrderList(List<Order> orders) {
        this.orders = orders;
    }
}

package com.watersolution.inventory.component.management.order.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.management.order.model.db.Order;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class OrderList extends ResponseDefault {
    @Valid
    private List<Order> orders;

    public OrderList(List<Order> orders) {
        this.orders = orders;
    }
}

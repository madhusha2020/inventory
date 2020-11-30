package com.watersolution.inventory.component.management.delivery.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.management.delivery.model.db.Delivery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class DeliveryList extends ResponseDefault {
    @Valid
    private List<Delivery> deliveryList;

    public DeliveryList(@Valid List<Delivery> deliveryList) {
        this.deliveryList = deliveryList;
    }
}

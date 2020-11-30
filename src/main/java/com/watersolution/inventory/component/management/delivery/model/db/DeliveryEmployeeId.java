package com.watersolution.inventory.component.management.delivery.model.db;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class DeliveryEmployeeId implements Serializable {

    @Column(name = "delivery_id")
    private long deliveryId;

    @Column(name = "employee_id")
    private long employeeId;

    public DeliveryEmployeeId() {
    }

    public DeliveryEmployeeId(long deliveryId, long employeeId) {
        this.deliveryId = deliveryId;
        this.employeeId = employeeId;
    }
}

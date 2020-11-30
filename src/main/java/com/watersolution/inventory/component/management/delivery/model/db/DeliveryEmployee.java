package com.watersolution.inventory.component.management.delivery.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.entity.employee.model.db.Employee;
import com.watersolution.inventory.component.management.inventory.model.db.Inventory;
import com.watersolution.inventory.component.management.sales.model.db.Sale;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "deliveryemployee")
@EqualsAndHashCode(callSuper = false)
public class DeliveryEmployee extends Auditable {

    @EmbeddedId
    private DeliveryEmployeeId deliveryEmployeeId;

    @JsonBackReference(value = "deliverydelivery")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("deliveryId")
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @JsonBackReference(value = "deliveryemployee")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public DeliveryEmployee() {
    }
}

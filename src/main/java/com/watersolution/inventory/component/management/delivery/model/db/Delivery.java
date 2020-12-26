package com.watersolution.inventory.component.management.delivery.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.entity.employee.model.db.Employee;
import com.watersolution.inventory.component.entity.vehicle.model.db.Vehicle;
import com.watersolution.inventory.component.management.sales.model.db.Sale;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "delivery")
@EqualsAndHashCode(callSuper = false)
public class Delivery extends Auditable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "delivery_seq")
    @SequenceGenerator(initialValue = 1, sequenceName = "delivery_seq", name = "delivery_seq")
    private long id;

    @Column(name = "code")
    private String code;

    @Lob
    private String description;

    @Column(name = "date")
    private LocalDate date;

    @Lob
    private String deliveryaddress;

    @Column(name = "deliverycontactname")
    private String deliverycontactname;

    @Column(name = "deliverycontactno")
    private String deliverycontactno;

    @Lob
    private String deliverynote;

    @JsonBackReference(value = "deliverysale")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @JsonBackReference(value = "deliveryvehicle")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @JsonBackReference(value = "deliveryemployee")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Transient
    private String deliveryempname;

    @Transient
    private String deliveryempcontactno;

    @Transient
    private String deliveryvehicletype;

    @Transient
    private String deliveryvehicleno;

    @Transient
    private long vehicleId;

    @Transient
    private long employeeId;

    public Delivery() {
    }
}

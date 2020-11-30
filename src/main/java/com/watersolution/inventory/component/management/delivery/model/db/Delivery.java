package com.watersolution.inventory.component.management.delivery.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.entity.vehicle.model.db.Vehicle;
import com.watersolution.inventory.component.management.sales.model.db.Sale;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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

    @ToString.Exclude
    @JsonManagedReference(value = "deliverydelivery")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "delivery", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DeliveryEmployee> deliveryEmployeeList;

    @JsonBackReference(value = "deliverysale")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @JsonManagedReference(value = "deliveryvehicle")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    public Delivery() {
    }
}

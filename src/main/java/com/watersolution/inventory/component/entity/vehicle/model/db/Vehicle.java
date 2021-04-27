package com.watersolution.inventory.component.entity.vehicle.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.management.delivery.model.db.Delivery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "vehicle")
@EqualsAndHashCode(callSuper = false)
public class Vehicle extends Auditable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_seq")
    @SequenceGenerator(initialValue = 4, sequenceName = "vehicle_seq", name = "vehicle_seq")
    private long id;

    @Column(name = "no")
    private String no;

    @Lob
    private String description;

    @Column(name = "brand")
    private String brand;

    @Column(name = "modal")
    private String modal;

    @JsonBackReference(value = "vehicletype")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicletype_id")
    private VehicleType vehicleType;

    @ToString.Exclude
    @JsonManagedReference(value = "vehiclefacilityvehicle")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicle", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<VehicleVehicleFacility> vehicleFacilityList;

    @ToString.Exclude
    @JsonManagedReference(value = "deliveryvehicle")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicle", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Delivery> deliveryList;

    @Transient
    private String type;

    public Vehicle() {
    }
}

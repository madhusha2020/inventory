package com.watersolution.inventory.component.entity.vehicle.model.db;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "vehicletype")
@EqualsAndHashCode(callSuper = false)
public class VehicleType extends Auditable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_type_seq")
    @SequenceGenerator(initialValue = 6, sequenceName = "vehicle_type_seq", name = "vehicle_type_seq")
    private long id;

    @Column(name = "name")
    private String name;

    @ToString.Exclude
    @JsonManagedReference(value = "vehicletype")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicleType", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Vehicle> vehicleList;

    public VehicleType() {
    }
}

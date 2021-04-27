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
@Table(name = "vehiclefacility")
@EqualsAndHashCode(callSuper = false)
public class VehicleFacility extends Auditable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_facility_seq")
    @SequenceGenerator(initialValue = 4, sequenceName = "vehicle_facility_seq", name = "vehicle_facility_seq")
    private long id;

    @Column(name = "name")
    private String name;

    @ToString.Exclude
    @JsonManagedReference(value = "vehiclefacility")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicleFacility", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<VehicleVehicleFacility> vehicleFacilityList;

    public VehicleFacility() {
    }
}

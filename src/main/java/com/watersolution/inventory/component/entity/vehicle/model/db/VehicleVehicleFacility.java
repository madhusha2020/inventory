package com.watersolution.inventory.component.entity.vehicle.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "vehiclevehiclefacility")
@EqualsAndHashCode(callSuper = false)
public class VehicleVehicleFacility extends Auditable {

    @EmbeddedId
    private VehicleVechileFacilityId vehicleVechileFacilityId;

    @JsonBackReference(value = "vehiclefacilityvehicle")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("vehicleId")
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @JsonBackReference(value = "vehiclefacility")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("vehicleFacilityId")
    @JoinColumn(name = "vehiclefacility_id")
    private VehicleFacility vehicleFacility;

    public VehicleVehicleFacility() {
    }
}

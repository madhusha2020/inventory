package com.watersolution.inventory.component.entity.vehicle.model.db;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class VehicleVechileFacilityId implements Serializable {

    @Column(name = "vehicle_id")
    private long vehicleId;

    @Column(name = "vehiclefacility_id")
    private long vehicleFacilityId;

    public VehicleVechileFacilityId() {
    }

    public VehicleVechileFacilityId(long vehicleId, long vehicleFacilityId) {
        this.vehicleId = vehicleId;
        this.vehicleFacilityId = vehicleFacilityId;
    }
}

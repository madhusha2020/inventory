package com.watersolution.inventory.component.entity.vehicle.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.entity.vehicle.model.db.Vehicle;
import com.watersolution.inventory.component.entity.vehicle.model.db.VehicleFacility;
import com.watersolution.inventory.component.entity.vehicle.model.db.VehicleType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class VehicleFacilityList extends ResponseDefault {
    @Valid
    private Vehicle vehicle;
    @Valid
    private VehicleType vehicleType;
    @Valid
    private List<VehicleFacility> vehicleFacilityList;

    public VehicleFacilityList(@Valid Vehicle vehicle, @Valid VehicleType vehicleType, @Valid List<VehicleFacility> vehicleFacilityList) {
        this.vehicle = vehicle;
        this.vehicleType = vehicleType;
        this.vehicleFacilityList = vehicleFacilityList;
    }
}

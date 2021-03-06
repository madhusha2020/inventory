package com.watersolution.inventory.component.entity.vehicle.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.entity.vehicle.model.db.Vehicle;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class VehicleList extends ResponseDefault {
    @Valid
    private List<Vehicle> vehicleList;

    public VehicleList(@Valid List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }
}

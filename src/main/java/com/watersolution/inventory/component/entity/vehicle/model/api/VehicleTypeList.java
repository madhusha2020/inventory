package com.watersolution.inventory.component.entity.vehicle.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.entity.vehicle.model.db.VehicleType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class VehicleTypeList extends ResponseDefault {

    @Valid
    private List<VehicleType> vehicleTypeList;

    public VehicleTypeList(@Valid List<VehicleType> vehicleTypeList) {
        this.vehicleTypeList = vehicleTypeList;
    }
}

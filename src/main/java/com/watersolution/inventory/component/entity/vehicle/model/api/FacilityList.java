package com.watersolution.inventory.component.entity.vehicle.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.entity.vehicle.model.db.VehicleFacility;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class FacilityList extends ResponseDefault {
    @Valid
    private List<VehicleFacility> vehicleFacilityList;

    public FacilityList(@Valid List<VehicleFacility> vehicleFacilityList) {
        this.vehicleFacilityList = vehicleFacilityList;
    }
}

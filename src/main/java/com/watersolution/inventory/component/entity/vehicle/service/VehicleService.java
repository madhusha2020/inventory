package com.watersolution.inventory.component.entity.vehicle.service;

import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.entity.vehicle.model.api.FacilityList;
import com.watersolution.inventory.component.entity.vehicle.model.api.VehicleFacilityList;
import com.watersolution.inventory.component.entity.vehicle.model.api.VehicleList;
import com.watersolution.inventory.component.entity.vehicle.model.api.VehicleTypeList;
import com.watersolution.inventory.component.entity.vehicle.model.db.Vehicle;
import com.watersolution.inventory.component.entity.vehicle.model.db.VehicleFacility;

public interface VehicleService {

    VehicleList getAllVehicles();

    FacilityList getAllVehicleFacilities();

    VehicleTypeList getAllVehicleTypes();

    VehicleFacilityList saveVehicle(VehicleFacilityList vehicleFacilityList);

    VehicleFacility saveVehicleFacility(VehicleFacility vehicleFacility);

    Vehicle getVehicle(String vehicleId);

    VehicleFacility getVehicleFacility(String vehicleFacilityId);

    VehicleFacilityList updateVehicle(VehicleFacilityList vehicleFacilityList);

    VehicleFacility updateVehicleFacility(VehicleFacility vehicleFacility);

    Vehicle suspendVehicle(TransactionRequest transactionRequest);

    Vehicle activateVehicle(TransactionRequest transactionRequest);
}

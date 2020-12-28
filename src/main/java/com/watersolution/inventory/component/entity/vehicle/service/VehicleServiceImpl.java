package com.watersolution.inventory.component.entity.vehicle.service;

import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.common.validator.CustomValidator;
import com.watersolution.inventory.component.entity.vehicle.model.api.FacilityList;
import com.watersolution.inventory.component.entity.vehicle.model.api.VehicleFacilityList;
import com.watersolution.inventory.component.entity.vehicle.model.api.VehicleList;
import com.watersolution.inventory.component.entity.vehicle.model.api.VehicleTypeList;
import com.watersolution.inventory.component.entity.vehicle.model.db.Vehicle;
import com.watersolution.inventory.component.entity.vehicle.model.db.VehicleFacility;
import com.watersolution.inventory.component.entity.vehicle.model.db.VehicleVechileFacilityId;
import com.watersolution.inventory.component.entity.vehicle.model.db.VehicleVehicleFacility;
import com.watersolution.inventory.component.entity.vehicle.repository.FacilityRepository;
import com.watersolution.inventory.component.entity.vehicle.repository.VehicleRepository;
import com.watersolution.inventory.component.entity.vehicle.repository.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private FacilityRepository facilityRepository;
    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;
    @Autowired
    private CustomValidator customValidator;

    @Override
    public VehicleList getAllVehicles() {
        return new VehicleList(vehicleRepository.findAllByStatusIn(Status.getAllStatusAsList()).stream().map(this::mapVehicleDetails).collect(Collectors.toList()));
    }

    @Override
    public FacilityList getAllVehicleFacilities() {
        return new FacilityList(facilityRepository.findAllByStatusIn(Status.getAllStatusAsList()).stream().map(this::mapVehicleFacilityDetails).collect(Collectors.toList()));
    }

    @Override
    public VehicleTypeList getAllVehicleTypes() {
        return new VehicleTypeList(vehicleTypeRepository.findAllByStatusIn(Status.getAllStatusAsList()));
    }

    @Transactional
    @Override
    public VehicleFacilityList saveVehicle(VehicleFacilityList vehicleFacilityList) {

        vehicleFacilityList.getVehicle().setStatus(Status.ACTIVE.getValue());
        vehicleFacilityList.getVehicle().fillCompulsory(vehicleFacilityList.getVehicle().getUserId());
        vehicleFacilityList.getVehicle().setVehicleType(vehicleFacilityList.getVehicleType());
        Vehicle vehicle = vehicleRepository.save(vehicleFacilityList.getVehicle());

        List<VehicleVehicleFacility> vehicleVehicleFacilityList = new ArrayList<>();

        vehicleFacilityList.getVehicleFacilityList().stream().forEach(vehicleFacility -> {
            VehicleVehicleFacility vehicleVehicleFacility = new VehicleVehicleFacility();
            vehicleVehicleFacility.setVehicle(vehicle);
            vehicleVehicleFacility.setVehicleFacility(vehicleFacility);
            vehicleVehicleFacility.setVehicleVechileFacilityId(new VehicleVechileFacilityId(vehicle.getId(), vehicleFacility.getId()));
            vehicleVehicleFacility.setStatus(Status.ACTIVE.getValue());
            vehicleVehicleFacility.fillCompulsory(vehicle.getUserId());
            vehicleVehicleFacilityList.add(vehicleVehicleFacility);
        });
        vehicleFacilityList.getVehicle().setVehicleFacilityList(vehicleVehicleFacilityList);
        vehicleRepository.save(vehicleFacilityList.getVehicle());

        return vehicleFacilityList;
    }

    @Override
    public VehicleFacility saveVehicleFacility(VehicleFacility vehicleFacility) {
        vehicleFacility.setStatus(Status.ACTIVE.getValue());
        vehicleFacility.fillCompulsory(vehicleFacility.getUserId());
        return facilityRepository.save(vehicleFacility);
    }

    @Override
    public Vehicle getVehicle(String vehicleId) {
        customValidator.validateNullOrEmpty(vehicleId, "vehicleId");
        Vehicle vehicle = vehicleRepository.findByIdAndStatusIn(Long.valueOf(vehicleId), Status.getAllStatusAsList());
        customValidator.validateFoundNull(vehicle, "vehicle");
        return mapVehicleDetails(vehicle);
    }

    @Override
    public VehicleFacility getVehicleFacility(String vehicleFacilityId) {
        customValidator.validateNullOrEmpty(vehicleFacilityId, "vehicleFacilityId");
        VehicleFacility vehicleFacility = facilityRepository.findByIdAndStatusIn(Long.valueOf(vehicleFacilityId), Status.getAllStatusAsList());
        customValidator.validateFoundNull(vehicleFacility, "vehicle facility");
        return mapVehicleFacilityDetails(vehicleFacility);
    }

    @Override
    public VehicleFacilityList updateVehicle(VehicleFacilityList vehicleFacilityList) {

        vehicleFacilityList.getVehicle().setStatus(Status.ACTIVE.getValue());
        vehicleFacilityList.getVehicle().fillUpdateCompulsory(vehicleFacilityList.getVehicle().getUserId());
        vehicleFacilityList.getVehicle().setVehicleType(vehicleFacilityList.getVehicleType());
        Vehicle vehicle = vehicleRepository.save(vehicleFacilityList.getVehicle());

        List<VehicleVehicleFacility> vehicleVehicleFacilityList = new ArrayList<>();

        vehicleFacilityList.getVehicleFacilityList().stream().forEach(vehicleFacility -> {
            VehicleVehicleFacility vehicleVehicleFacility = new VehicleVehicleFacility();
            vehicleVehicleFacility.setVehicle(vehicle);
            vehicleVehicleFacility.setVehicleFacility(vehicleFacility);
            vehicleVehicleFacility.setVehicleVechileFacilityId(new VehicleVechileFacilityId(vehicle.getId(), vehicleFacility.getId()));
            vehicleVehicleFacility.setStatus(Status.ACTIVE.getValue());
            vehicleVehicleFacility.fillCompulsory(vehicle.getUserId());
            vehicleVehicleFacilityList.add(vehicleVehicleFacility);
        });
        vehicleFacilityList.getVehicle().setVehicleFacilityList(vehicleVehicleFacilityList);
        vehicleRepository.save(vehicleFacilityList.getVehicle());

        return vehicleFacilityList;
    }

    @Override
    public VehicleFacility updateVehicleFacility(VehicleFacility vehicleFacility) {
        return null;
    }

    @Override
    public Vehicle suspendVehicle(TransactionRequest transactionRequest) {

        Vehicle vehicle = vehicleRepository.findByIdAndStatusIn(transactionRequest.getId(), Status.getAllStatusAsList());
        vehicle.setStatus(Status.SUSPENDED.getValue());
        vehicle.fillUpdateCompulsory(transactionRequest.getUserId());
        vehicleRepository.save(vehicle);

        return vehicle;
    }

    @Override
    public Vehicle activateVehicle(TransactionRequest transactionRequest) {

        Vehicle vehicle = vehicleRepository.findByIdAndStatusIn(transactionRequest.getId(), Status.getAllStatusAsList());
        vehicle.setStatus(Status.ACTIVE.getValue());
        vehicle.fillUpdateCompulsory(transactionRequest.getUserId());
        vehicleRepository.save(vehicle);

        return vehicle;
    }

    private Vehicle mapVehicleDetails(Vehicle vehicle) {
        vehicle.setType(vehicle.getVehicleType().getName());
        return vehicle;
    }

    private VehicleFacility mapVehicleFacilityDetails(VehicleFacility vehicleFacility) {
        return vehicleFacility;
    }
}

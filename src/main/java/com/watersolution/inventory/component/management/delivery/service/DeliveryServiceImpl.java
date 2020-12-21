package com.watersolution.inventory.component.management.delivery.service;

import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.common.validator.CustomValidator;
import com.watersolution.inventory.component.entity.employee.service.EmployeeService;
import com.watersolution.inventory.component.entity.vehicle.service.VehicleService;
import com.watersolution.inventory.component.management.delivery.model.api.DeliveryList;
import com.watersolution.inventory.component.management.delivery.model.db.Delivery;
import com.watersolution.inventory.component.management.delivery.repository.DeliveryRepository;
import com.watersolution.inventory.component.management.notification.service.NotificationService;
import com.watersolution.inventory.component.management.order.model.db.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private CustomValidator customValidator;


    @Override
    public DeliveryList getAllDeliveries() {
        return new DeliveryList(deliveryRepository.findAllByStatusIn(Status.getAllStatusAsList()).stream().map(this::mapDeliveryDetails).collect(Collectors.toList()));
    }


    @Override
    public Delivery getDeliveryById(String deliveryId) {
        customValidator.validateNullOrEmpty(deliveryId, "deliveryId");
        Delivery delivery = deliveryRepository.findByIdAndStatusIn(Long.valueOf(deliveryId), Status.getAllStatusAsList());
        customValidator.validateFoundNull(delivery, "delivery");
        return mapDeliveryDetails(delivery);
    }

    @Transactional
    @Override
    public Delivery saveDelivery(Order order) {
        Delivery delivery = new Delivery();
        delivery.setSale(order.getSale());
        delivery.setDeliveryaddress(order.getCustomer().getAddress());
        delivery.setDeliverycontactname(order.getCustomer().getName());
        delivery.setDeliverycontactno(order.getCustomer().getContact1());
        delivery.setStatus(Status.PENDING.getValue());
        delivery.fillCompulsory(order.getUserId());
        deliveryRepository.save(delivery);

        notificationService.deliveryNotification(delivery);

        return delivery;
    }

    @Transactional
    @Override
    public Delivery updateDelivery(Delivery delivery) {
        delivery.fillUpdateCompulsory(delivery.getUserId());
        return mapDeliveryDetails(deliveryRepository.save(delivery));
    }

    private Delivery mapDeliveryDetails(Delivery delivery) {
        if (delivery.getStatus().equals(Status.ACTIVE.getValue()) || delivery.getStatus().equals(Status.SUSPENDED.getValue())) {
            delivery.setDeliveryempname(delivery.getDeliveryEmployeeList().stream().findAny().get().getEmployee().getName());
            delivery.setDeliverycontactno(delivery.getDeliveryEmployeeList().stream().findAny().get().getEmployee().getMobile());
            delivery.setDeliveryvehicletype(delivery.getVehicle().getModal());
            delivery.setDeliverycontactno(delivery.getVehicle().getNo());
        }
        return delivery;
    }
}

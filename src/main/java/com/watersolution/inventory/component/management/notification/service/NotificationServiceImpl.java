package com.watersolution.inventory.component.management.notification.service;

import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.common.util.AlertType;
import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.management.delivery.model.db.Delivery;
import com.watersolution.inventory.component.management.inventory.model.db.Inventory;
import com.watersolution.inventory.component.management.notification.model.api.NotificationList;
import com.watersolution.inventory.component.management.notification.model.db.Notification;
import com.watersolution.inventory.component.management.notification.repository.NotificationRepository;
import com.watersolution.inventory.component.management.role.model.db.Module;
import com.watersolution.inventory.component.management.role.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public NotificationList getNotificationsByUser(TransactionRequest transactionRequest) {
        return new NotificationList(notificationRepository.findAllByUserNameAndStatusIn(transactionRequest.getUserId(), Status.getAllStatusAsList()));
    }

    @Override
    public NotificationList getNewNotificationsByUser(TransactionRequest transactionRequest) {
        List<Notification> updatableNotificationList = new ArrayList<>();
        List<Notification> notificationList = notificationRepository.findAllByUserNameAndStatus(transactionRequest.getUserId(), Status.PENDING.getValue());
        notificationList.stream().forEach(notification -> {
            notification.setStatus(Status.ACTIVE.getValue());
            updatableNotificationList.add(notification);
        });
        notificationRepository.saveAll(updatableNotificationList);
        return new NotificationList(notificationList);
    }

    @Override
    public void inventoryNotification(Inventory inventory) {

        List<Notification> notificationList = new ArrayList<>();

        getUserList("INV-PO-CR").stream().forEach(userName -> {
            Notification notification = new Notification();
            notification.setDosend(LocalDate.now());
            notification.setUserName(userName);
            notification.setMessage("Item #" + inventory.getItem().getCode() + " is out of stock!");
            notification.setType(AlertType.INVENTORY_ALERT.getValue());
            notification.fillCompulsory("SYSTEM");
            notification.setStatus(Status.PENDING.getValue());
            notificationList.add(notification);
        });

        notificationRepository.saveAll(notificationList);
    }

    @Override
    public void deliveryNotification(Delivery delivery) {

        List<Notification> notificationList = new ArrayList<>();

        getUserList("INV-DEL-CR").stream().forEach(userName -> {
            Notification notification = new Notification();
            notification.setDosend(LocalDate.now());
            notification.setUserName(userName);
            notification.setMessage("Delivery #" + delivery.getId() + " is awaiting for approval!");
            notification.setType(AlertType.DELIVERY_ALERT.getValue());
            notification.fillCompulsory("SYSTEM");
            notification.setStatus(Status.PENDING.getValue());
            notificationList.add(notification);
        });

        notificationRepository.saveAll(notificationList);
    }

    private List<String> getUserList(String permission) {
        List<String> userNameList = new ArrayList<>();
        Module module = moduleRepository.findByPermissionCode(permission);
        module.getPrivileges().stream().forEach(privilege -> {
            privilege.getRole().getUserRoles().stream().forEach(userRole -> {
                userNameList.add(userRole.getUser().getUserName());
            });
        });
        return userNameList;
    }
}

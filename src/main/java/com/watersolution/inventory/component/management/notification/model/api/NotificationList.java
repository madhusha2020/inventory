package com.watersolution.inventory.component.management.notification.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.management.notification.model.db.Notification;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class NotificationList extends ResponseDefault {

    private List<Notification> notificationList;

    public NotificationList(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }
}

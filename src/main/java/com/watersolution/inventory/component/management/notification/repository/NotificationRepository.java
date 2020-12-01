package com.watersolution.inventory.component.management.notification.repository;

import com.watersolution.inventory.component.management.notification.model.db.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByUserNameAndStatusIn(String userName, List<Integer> statusList);

    List<Notification> findAllByUserNameAndStatus(String userName, int status);
}

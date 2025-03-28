package com.gnr.ecommorce.notification.repository;

import com.gnr.ecommorce.notification.document.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}

package com.notificationService.repository;

import com.notificationService.entities.NotifyDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<NotifyDTO, Integer> {

}

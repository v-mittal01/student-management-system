package com.notificationService.serviceImpl;


import com.notificationService.entities.NotifyDTO;
import com.notificationService.repository.NotificationRepository;
import com.notificationService.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationRepository notifRepository;

    @KafkaListener(topics = "${spring.kafka.topics}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) {
        System.out.println("Message from notification service : " + message);
        NotifyDTO nn = new NotifyDTO();
        nn.setId(SequenceGeneratorSerivce.generateSequence(NotifyDTO.SEQUENCE_NAME));
        nn.setDate(new Date());
        nn.setNotification(message);
        try {
            notifRepository.save(nn);
            System.out.println("Data saved successfully.");
        } catch (Exception e) {
            System.err.println("Data Not saved.");
            e.printStackTrace();
        }

    }
}

package com.notificationService.service;

public interface NotificationService {

    void consume(String message); // kafka consumer
}
package com.notificationService.entities;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "${mongo.collection.name}")
public class NotifyDTO {

    @Transient // to exclude this property from the serialization process. i.e. it won't be saved in DB/file
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private long notificationId;

    @CreatedDate
    private Date date;

    private String notification;

    public NotifyDTO(String notification) { // parametric constructor
        this.notification = notification;
    }

    public NotifyDTO() { // default constructor
    }

    public NotifyDTO(long notificationId, Date date, String notification) { // parametric constructor
        super();
        this.notificationId = notificationId;
        this.date = date;
        this.notification = notification;
    }

    public static String getSequenceName() {
        return SEQUENCE_NAME;
    }

    public long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(long notificationId) {
        this.notificationId = notificationId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

}

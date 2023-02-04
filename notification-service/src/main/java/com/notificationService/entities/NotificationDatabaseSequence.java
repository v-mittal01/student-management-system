package com.notificationService.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "database_sequences")
public class NotificationDatabaseSequence { // this entity is created to achieve auto generated values(long seq)

    @Id
    private String id;

    private long seq;

    public NotificationDatabaseSequence() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }
}
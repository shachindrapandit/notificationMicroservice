package com.cassandra.docker.springboot.CassandraDockerSpringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("alertMessage")
public class NotificationModel {

    @PrimaryKey
    private int id;
    private String emailId;
    private String notification_body;
    private String notification_header;
    private boolean sendEmailSuccess;

    public NotificationModel() {
    }

    public NotificationModel(int id, String emailId, String notification_body, String notification_header, boolean sendEmailSuccess) {
        this.id = id;
        this.emailId = emailId;
        this.notification_body = notification_body;
        this.notification_header = notification_header;
        this.sendEmailSuccess = sendEmailSuccess;
    }

    @Override
    public String toString() {
        return "NotificationModel{" +
                "id=" + id +
                ", emailId='" + emailId + '\'' +
                ", notification_body='" + notification_body + '\'' +
                ", notification_header='" + notification_header + '\'' +
                ", sendEmailSuccess=" + sendEmailSuccess +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getNotification_body() {
        return notification_body;
    }

    public void setNotification_body(String notification_body) {
        this.notification_body = notification_body;
    }

    public String getNotification_header() {
        return notification_header;
    }

    public void setNotification_header(String notification_header) {
        this.notification_header = notification_header;
    }

    public boolean isSendEmailSuccess() {
        return sendEmailSuccess;
    }

    public void setSendEmailSuccess(boolean sendEmailSuccess) {
        this.sendEmailSuccess = sendEmailSuccess;
    }
}

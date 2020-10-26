package com.cassandra.docker.springboot.CassandraDockerSpringboot.service;

import com.cassandra.docker.springboot.CassandraDockerSpringboot.entity.NotificationModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${email.address}")
    private String attchEmailAddr;

    private static final Logger logger = 	LoggerFactory.getLogger(NotificationService.class);

    SimpleMailMessage msg = new SimpleMailMessage();

    public void sendNotificationTextEmail(NotificationModel notification) {

        if(notification.isSendEmailSuccess() == false) {
            try {
                if (notification.getEmailId().contains(",")) {
                    String[] emails = notification.getEmailId().split(",");
                    int receipantSize = emails.length;
                    for (int i = 0; i < receipantSize; i++) {

                        msg.setTo(emails[i]);
                        msg.setSubject(notification.getNotification_header());
                        msg.setText(notification.getNotification_body());
                        javaMailSender.send(msg);
                    }

                } else {
                    msg.setTo(notification.getEmailId());
                    msg.setSubject(notification.getNotification_header());
                    msg.setText(notification.getNotification_body());
                    javaMailSender.send(msg);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("No more updates to send to user ! ! !");
        }
    }




}

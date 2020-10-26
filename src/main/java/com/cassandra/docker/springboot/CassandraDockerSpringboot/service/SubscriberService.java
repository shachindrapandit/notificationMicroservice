package com.cassandra.docker.springboot.CassandraDockerSpringboot.service;

import com.cassandra.docker.springboot.CassandraDockerSpringboot.dto.UpdateTable;
import com.cassandra.docker.springboot.CassandraDockerSpringboot.entity.NotificationModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SubscriberService {

    private final Logger logger = LoggerFactory.getLogger(SubscriberService.class);

    @Autowired
    private NotificationService notificationService;

    @KafkaListener(topics = "users", groupId = "group_id", containerFactory = "userListner")
    public void consume(NotificationModel notification){
        notificationService.sendNotificationTextEmail(notification);
        System.out.println(" ****** Consumed Message ******");
    }


}

package com.cassandra.docker.springboot.CassandraDockerSpringboot.service;

import com.cassandra.docker.springboot.CassandraDockerSpringboot.dto.UpdateTable;
import com.cassandra.docker.springboot.CassandraDockerSpringboot.entity.NotificationModel;
import com.cassandra.docker.springboot.CassandraDockerSpringboot.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    private static final Logger logger = 	LoggerFactory.getLogger(NotificationService.class);

    private static final String TOPIC = "users";

    public UpdateTable table = new UpdateTable();

    @Autowired
    private KafkaTemplate<String, NotificationModel> kafkaTemplate;

    public boolean checkTableUpdates(NotificationModel m, int id, NotificationRepository repo){
        boolean f = false;
        f = table.doesCommit(m, repo);
        return f;
    }

    public void sendMessage(NotificationModel msg){
        logger.info(String.format("**** Producing message **** ", msg));
        this.kafkaTemplate.send(TOPIC, msg);
    }


}

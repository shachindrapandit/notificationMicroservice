package com.cassandra.docker.springboot.CassandraDockerSpringboot.controller;

import com.cassandra.docker.springboot.CassandraDockerSpringboot.dto.UpdateTable;
import com.cassandra.docker.springboot.CassandraDockerSpringboot.entity.NotificationModel;
import com.cassandra.docker.springboot.CassandraDockerSpringboot.repository.NotificationRepository;
import com.cassandra.docker.springboot.CassandraDockerSpringboot.service.NotificationService;
import com.cassandra.docker.springboot.CassandraDockerSpringboot.service.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v2/notification")
@Slf4j
public class NotificationController {

    public boolean flag = false;

    @Autowired
    private NotificationService service;

    @GetMapping(value="/healthCheck", consumes = "application/json", produces = "application/json;charset=utf-8")
    public String getHealthCheck(){
        return "{ \" is working \" : true }";
    }

    @PostMapping(value="/fromMS",consumes = "application/json", produces = "application/json")
    public String addMessage(@RequestBody NotificationModel newNotification){
        boolean save = service.save(newNotification);
        if(save == true)
            return "Published successfully . . .";
        else
            return "Error in processing . . .";
    }

/*    @PutMapping("/notification/{id}")
    public Optional<NotificationModel> updateNotification(@RequestBody NotificationModel newNotification,
                                                     @PathVariable int id)
    {

        Optional<NotificationModel> optionalNotification = repository.findById(id);
        if (optionalNotification.isPresent()) {
            NotificationModel notification = optionalNotification.get();
            notification.setId(newNotification.getId());
            notification.setEmailId(newNotification.getEmailId());
            notification.setNotification_body(newNotification.getNotification_body());
            notification.setNotification_header(newNotification.getNotification_header());
            notification.setSendEmailSuccess(newNotification.isSendEmailSuccess());
            repository.save(notification);
        }
        return optionalNotification;

    }
*/


}

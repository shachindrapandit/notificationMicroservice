package com.cassandra.docker.springboot.CassandraDockerSpringboot.dto;

import com.cassandra.docker.springboot.CassandraDockerSpringboot.entity.NotificationModel;
import com.cassandra.docker.springboot.CassandraDockerSpringboot.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateTable {

    private NotificationModel notification;

    public Optional<NotificationModel> updateNotification(NotificationModel newNotification,
                                                          int id, NotificationRepository repo)
    {
        Optional<NotificationModel> optionalNotification = repo.findById(id);
        if (optionalNotification.isPresent()) {
            notification = optionalNotification.get();
            if(notification.isSendEmailSuccess() == true){
                return optionalNotification;
            }else {
                notification.setSendEmailSuccess(true);
                repo.save(notification);
            }
        }
        return optionalNotification;
    }

    public boolean doesCommit(NotificationModel model, NotificationRepository repo){
       updateNotification(model, model.getId(), repo);

        if(notification.isSendEmailSuccess() == true){
            return true;
        }
        return false;
    }

}

package com.cassandra.docker.springboot.CassandraDockerSpringboot.repository;

import com.cassandra.docker.springboot.CassandraDockerSpringboot.entity.NotificationModel;
import org.springframework.context.annotation.Bean;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends CassandraRepository<NotificationModel, Integer> {
    List<NotificationModel> findAll();
}

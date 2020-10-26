package com.cassandra.docker.springboot.CassandraDockerSpringboot.config;

import com.cassandra.docker.springboot.CassandraDockerSpringboot.entity.NotificationModel;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaSubscriberConfig {

    // Function to establish a connection
    // between Spring application
    // and Kafka server
    @Bean
    public ConsumerFactory<String, NotificationModel>
    userConsumer()
    {

        // HashMap to store the configurations
        Map<String, Object> map
                = new HashMap<>();

        // put the host IP in the map
        map.put(ConsumerConfig
                        .BOOTSTRAP_SERVERS_CONFIG,
                "127.0.0.1:9092");

        // put the group ID of consumer in the map
        map.put(ConsumerConfig
                        .GROUP_ID_CONFIG,
                "id");
        map.put(ConsumerConfig
                        .KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        map.put(ConsumerConfig
                        .VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class);

        // return message in JSON formate
        return new DefaultKafkaConsumerFactory<>(
                map, new StringDeserializer(),
                new JsonDeserializer<>(NotificationModel.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,
            NotificationModel>
    userListner()
    {
        ConcurrentKafkaListenerContainerFactory<String,
                NotificationModel>
                factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userConsumer());
        return factory;
    }


}

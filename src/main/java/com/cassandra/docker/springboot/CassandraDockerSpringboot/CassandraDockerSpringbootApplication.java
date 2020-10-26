package com.cassandra.docker.springboot.CassandraDockerSpringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCassandraRepositories
@ComponentScan({"com.cassandra.docker.springboot.CassandraDockerSpringboot.config",
"com.cassandra.docker.springboot.CassandraDockerSpringboot.controller",
"com.cassandra.docker.springboot.CassandraDockerSpringboot.dto",
"com.cassandra.docker.springboot.CassandraDockerSpringboot.entity",
"com.cassandra.docker.springboot.CassandraDockerSpringboot.repository",
"com.cassandra.docker.springboot.CassandraDockerSpringboot.service"})
public class CassandraDockerSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(CassandraDockerSpringbootApplication.class, args);
      /*  ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(CassandraDockerSpringbootApplication.class)
                .properties("spring.config.name:myapp")
                .build()
                .run(args);*/

    }

}

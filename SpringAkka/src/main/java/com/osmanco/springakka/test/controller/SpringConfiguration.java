package com.osmanco.springakka.test.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;



@SpringBootApplication
@ComponentScan(basePackages = "com.osmanco")
//@EnableCassandraRepositories("com.osmanco.springakka.cassandra.repositories")
//@EnableMongoRepositories("com.osmanco.springakka.mongo.repositories")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class SpringConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(SpringConfiguration.class, args);
    }
}
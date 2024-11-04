package com.hpugs.testweb.mongo.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Value("${mongo.connectionAddress}")
    private String connectionAddress;

    @Value("${mongo.databaseName}")
    private String databaseName;

    @Bean(name = "mongoClient")
    MongoClient init() {
        MongoClient mongoClient = MongoClients.create(connectionAddress);
        return mongoClient;
    }

    @Bean
    MongoDatabase initDatabase(@Qualifier("mongoClient") MongoClient mongoClient) {
        MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);
        return mongoDatabase;
    }

}

package com.hpugs.testweb.mongo.config;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Configuration
public class MongoConfig {

    @Value("${mongo.host}")
    private String host;

    @Value("${mongo.port}")
    private Integer port;

    @Value("${mongo.username}")
    private String username;

    @Value("${mongo.password}")
    private String password;

    @Value("${mongo.databaseName}")
    private String databaseName;

    @Bean(name = "mongoClient")
    MongoClient init() throws UnsupportedEncodingException {
        String usernameEncoder = URLEncoder.encode(username, "utf-8");
        String passwordEncoder = URLEncoder.encode(password, "utf-8");
        ConnectionString connectionString = new ConnectionString("mongodb://" + usernameEncoder + ":" + passwordEncoder + "@" + host + ":" + port);
        MongoClient mongoClient = MongoClients.create(connectionString);
        return mongoClient;
    }

    @Bean
    MongoDatabase initDatabase(@Qualifier("mongoClient") MongoClient mongoClient) {
        MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);
        return mongoDatabase;
    }

}

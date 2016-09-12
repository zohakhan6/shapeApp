package com.shape.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mongodb.MongoClient;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.shape.app")
public class ShapeAppConfiguration {

    public @Bean MongoDbFactory getMongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClient("localhost", 27017), "shapeApp");
    }

    public @Bean MongoTemplate getMongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(getMongoDbFactory());
        return mongoTemplate;
    }
}
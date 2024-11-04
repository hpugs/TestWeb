package com.hpugs.testweb.mongo;

import com.hpugs.testweb.dto.UserDTO;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MongoService {

    private static final String COLLECTION_NAME = "users";

    @Resource
    private MongoDatabase mongoDatabase;

    public void insert(UserDTO userDTO) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION_NAME);

        Document document = new Document("id", userDTO.getId())
                .append("name", userDTO.getName())
                .append("email", userDTO.getEmail())
                .append("age", userDTO.getAge());

        collection.insertOne(document);
    }

    public String queryAll() {
        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION_NAME);

        StringBuilder stringBuilder = new StringBuilder();
        FindIterable<Document> documents = collection.find();
        for (Document document : documents) {
            stringBuilder.append(document.toJson());
        }
        return stringBuilder.toString();
    }

    public String queryByAge(Integer age) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION_NAME);

        StringBuilder stringBuilder = new StringBuilder();
        FindIterable<Document> documents = collection.find(Filters.lt("age", age));
        for (Document document : documents) {
            stringBuilder.append(document.toJson());
        }
        return stringBuilder.toString();
    }

    public void update(UserDTO userDTO) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION_NAME);
        collection.updateOne(Filters.eq("id", userDTO.getId()), Updates.set("age", userDTO.getAge()));
    }

    public void delete(UserDTO userDTO) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION_NAME);
        collection.deleteOne(Filters.eq("id", userDTO.getId()));
    }

}

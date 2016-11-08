package com.softserveinc.reviewer.health;

import java.util.concurrent.Callable;

import com.google.inject.Inject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoTimeoutException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import com.softserveinc.reviewer.config.MongoConfiguration;
import org.bson.Document;

public class MongoDBHealthCheck implements Callable<Boolean> {
    private final MongoConfiguration mongoConfiguration;

    @Inject
    public MongoDBHealthCheck(MongoConfiguration mongoConfiguration) {
        this.mongoConfiguration = mongoConfiguration;
    }

    @Override
    public Boolean call() throws Exception {
        ServerAddress serverAddress = new ServerAddress(mongoConfiguration.getHost(), mongoConfiguration.getPort());
        MongoClientOptions mongoClientOptions = MongoClientOptions.builder().serverSelectionTimeout(250).build();
        try {
            MongoClient mongoClient = new MongoClient(serverAddress, mongoClientOptions);
            MongoDatabase database = mongoClient.getDatabase(mongoConfiguration.getDatabase());
            database.runCommand(new Document("dbstats", 1));
            return true;
        } catch (MongoTimeoutException e) {
            return false;
        }
    }

}

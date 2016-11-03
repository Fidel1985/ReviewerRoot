package com.softserveinc.reviewer.config;

import com.mongodb.MongoClient;

public class MongoClientProvider extends MongoClient {
    private String databaseName;
    private String collectionName;

    public MongoClientProvider(String host, int port, String databaseName, String collectionName) {
        super(host, port);
        this.databaseName = databaseName;
        this.collectionName = collectionName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getCollectionName() {
        return collectionName;
    }
}

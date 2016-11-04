package com.softserveinc.reviewer.dao;

import static com.mongodb.client.model.Filters.eq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.softserveinc.reviewer.annotation.Collection;
import com.softserveinc.reviewer.annotation.Database;
import com.softserveinc.reviewer.model.Statistic;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

public class StatisticDaoMongoNativeImpl implements StatisticDao{
    private final Provider<MongoClient> mongoClientProvider;
    private final String database;
    private final String collection;

    @Inject
    public StatisticDaoMongoNativeImpl(Provider<MongoClient> mongoClientProvider, @Database String database,
            @Collection String collection) {
        this.mongoClientProvider = mongoClientProvider;
        this.database = database;
        this.collection = collection;
    }

    @Override
    public Statistic getOneByMethodName(String methodName) {
        MongoCollection collection = getCollection();
        ObjectMapper mapper = new ObjectMapper();
        Object object = collection.find(eq("name", methodName)).first();
        return mapper.convertValue(object, Statistic.class);
    }

    @Override
    public Statistic create(Statistic statistic) {
        MongoCollection collection = getCollection();
        ObjectMapper mapper = new ObjectMapper();
        try {
            String userJson = mapper.writeValueAsString(statistic);
            Document userDoc = Document.parse(userJson);
            collection.insertOne(userDoc);
            return statistic;
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @Override
    public Statistic update(Statistic statistic) {
        MongoCollection collection = getCollection();
        ObjectMapper mapper = new ObjectMapper();
        try {
            String userJson = mapper.writeValueAsString(statistic);
            Bson bson = BsonDocument.parse(userJson);
            Bson update = new Document("$set", bson);
            collection.findOneAndUpdate(eq("name", statistic.getMethodName()), update);
            return statistic;
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    private MongoCollection getCollection() {
        MongoDatabase db = mongoClientProvider.get().getDatabase(database);
        return db.getCollection(collection);
    }

}

package com.softserveinc.reviewer.dao;

import static com.mongodb.client.model.Filters.eq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.softserveinc.reviewer.model.Statistic;

public class StatisticDaoMongoNativeImpl implements StatisticDao{
    private static final String DATABASE_NAME = "test";
    private static final String COLLECTION_NAME = "collection";
    private final Provider<MongoClient> mongoClientProvider;

    @Inject
    public StatisticDaoMongoNativeImpl(Provider<MongoClient> mongoClientProvider) {
        this.mongoClientProvider = mongoClientProvider;
    }

    @Override
    public Statistic getOneByMethodName(String methodName) {
        MongoCollection collection = getCollection();
        ObjectMapper mapper = new ObjectMapper();
        Object o = collection.find(eq("name", methodName)).first();

        Statistic statistic = mapper.convertValue(o, Statistic.class);
        return statistic;
    }

    @Override
    public Statistic create(Statistic statistic) {
        return null;
    }

    @Override
    public Statistic update(Statistic statistic) {
        return null;
    }

    private MongoCollection getCollection() {
        MongoDatabase db = mongoClientProvider.get().getDatabase(DATABASE_NAME);
        return db.getCollection(COLLECTION_NAME);
    }

}

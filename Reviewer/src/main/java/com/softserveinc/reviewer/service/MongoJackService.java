package com.softserveinc.reviewer.service;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.softserveinc.reviewer.model.Statistic;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

public class MongoJackService {
    private Provider<MongoClient> mongoClientProvider;

    @Inject
    public MongoJackService(Provider<MongoClient> mongoClientProvider) {
        this.mongoClientProvider = mongoClientProvider;
    }

    public void invoke() {
        JacksonDBCollection<Statistic, String> collection = JacksonDBCollection.wrap(getCollection(), Statistic.class, String.class);
        Statistic statistic = new Statistic();
        statistic.setName("test name");
        WriteResult<Statistic, String> result = collection.insert(statistic);
        String id = result.getSavedId();
        Statistic foundStatistic = collection.findOneById(id);
        String string = null;

    }

    private DBCollection getCollection() {
        DB db = mongoClientProvider.get().getDB("test");
        return db.getCollection("collection");
    }
}

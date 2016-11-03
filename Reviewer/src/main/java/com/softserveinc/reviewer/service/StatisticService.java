package com.softserveinc.reviewer.service;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.softserveinc.reviewer.model.Statistic;
import org.mongojack.DBCursor;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;

public class StatisticService {
    private Provider<MongoClient> mongoClientProvider;

    @Inject
    public StatisticService(Provider<MongoClient> mongoClientProvider) {
        this.mongoClientProvider = mongoClientProvider;
    }

    public void saveStats(double duration, String methodName) {
        JacksonDBCollection<Statistic, String> collection = JacksonDBCollection.wrap(getCollection(), Statistic.class, String.class);
        DBObject query = new BasicDBObject("name", "getSyndicationMatches");
        DBCursor<Statistic> cursor = collection.find(DBQuery.is("name", "getSyndicationMatches"));
        Statistic statistic;
        try {
            statistic = cursor.next();
            if (statistic != null) {
                int invocationCount = statistic.getInvocationCount();
                double oldAverage = statistic.getAverage();
                double newAverage = (invocationCount * oldAverage + duration) / ++invocationCount;
                DBObject update = new BasicDBObject();
                Map<String, Object> map = new HashMap<>();
                map.put("average", Math.round(newAverage * 10.0) / 10.0);
                map.put("count", invocationCount);
                //update.put("$set", new BasicDBObject("average", Math.round(newAverage * 10.0) / 10.0));
                //update.put("$set", new BasicDBObject("count", invocationCount));
                //statistic.setAverage(Math.round(newAverage * 10.0) / 10.0);
                //statistic.setInvocationCount(invocationCount);
                update.put("$set", map);
                collection.findAndModify(query, update);
            }
        } catch (NoSuchElementException e) {
            statistic = new Statistic();
            statistic.setMethodName(methodName);
            statistic.setInvocationCount(1);
            statistic.setAverage(Math.round(duration * 10.0) / 10.0);
            collection.insert(statistic);
        }
    }

    private DBCollection getCollection() {
        DB db = mongoClientProvider.get().getDB("test");
        return db.getCollection("collection");
    }
}

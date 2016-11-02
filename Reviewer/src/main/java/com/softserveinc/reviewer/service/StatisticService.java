package com.softserveinc.reviewer.service;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.softserveinc.reviewer.model.Statistic;
import org.mongojack.DBCursor;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

public class StatisticService {
    private Provider<MongoClient> mongoClientProvider;

    @Inject
    public StatisticService(Provider<MongoClient> mongoClientProvider) {
        this.mongoClientProvider = mongoClientProvider;
    }

    public void saveStats(double duration, String methodName) {
        JacksonDBCollection<Statistic, String> collection = JacksonDBCollection.wrap(getCollection(), Statistic.class, String.class);
        DBCursor<Statistic> cursor = collection.find(DBQuery.is("methodName", "getSyndicationMatches"));
        Statistic statistic = cursor.next();
        if(statistic != null) {
            int invocationCount = statistic.getInvocationCount();
            double oldAverage = statistic.getAverage();
            double newAverage = (invocationCount * oldAverage + duration) / ++invocationCount;
            statistic.setInvocationCount(invocationCount);
            statistic.setAverage(newAverage);

            collection.findAndModify(DBQuery.is("methodName", "getSyndicationMatches"), statistic, null, true, null, true, true);
        }
        else {
            statistic = new Statistic();
            statistic.setMethodName(methodName);
            statistic.setInvocationCount(1);
            statistic.setAverage(Math.round(duration * 10.0) / 10.0);
            WriteResult<Statistic, String> result = collection.insert(statistic);
        }
    }

    private DBCollection getCollection() {
        DB db = mongoClientProvider.get().getDB("test");
        return db.getCollection("collection");
    }
}

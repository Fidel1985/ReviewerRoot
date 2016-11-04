package com.softserveinc.reviewer.dao;

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
import com.softserveinc.reviewer.annotation.Collection;
import com.softserveinc.reviewer.annotation.Database;
import com.softserveinc.reviewer.model.Statistic;
import org.mongojack.DBCursor;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;

public class StatisticDaoMongoJackImpl implements StatisticDao {
    private final Provider<MongoClient> mongoClientProvider;
    private final String database;
    private final String collection;

    @Inject
    public StatisticDaoMongoJackImpl(Provider<MongoClient> mongoClientProvider, @Database String database,
            @Collection String collection) {
        this.mongoClientProvider = mongoClientProvider;
        this.database = database;
        this.collection = collection;
    }

    @Override
    public Statistic getOneByMethodName(String methodName) {
        JacksonDBCollection<Statistic, String> collection = getWrappedCollection();
        DBCursor<Statistic> cursor = collection.find(DBQuery.is("name", methodName));
        try {
            return cursor.next();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public Statistic create(Statistic statistic) {
        JacksonDBCollection<Statistic, String> collection = getWrappedCollection();
        collection.insert(statistic);
        return statistic;
    }

    @Override
    public Statistic update(Statistic statistic) {
        JacksonDBCollection<Statistic, String> collection = getWrappedCollection();
        DBObject query = new BasicDBObject("name", statistic.getMethodName());
        DBObject update = new BasicDBObject();
        Map<String, Object> map = new HashMap<>();
        map.put("average", statistic.getAverage());
        map.put("count", statistic.getInvocationCount());
        update.put("$set", map);
        collection.findAndModify(query, update);
        return statistic;
    }

    private DBCollection getCollection() {
        DB db = mongoClientProvider.get().getDB(database);
        return db.getCollection(collection);
    }

    private JacksonDBCollection<Statistic, String> getWrappedCollection() {
        return JacksonDBCollection.wrap(getCollection(), Statistic.class, String.class);
    }
}

package com.softserveinc.reviewer.injector;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.mongodb.MongoClient;
import com.softserveinc.reviewer.config.DatabaseConfiguration;

public class MongoClientModule extends AbstractModule {
    private final DatabaseConfiguration configuration;

    public MongoClientModule(DatabaseConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void configure() {
    }

    @Provides
    @Singleton
    MongoClient provideMongoClient() {
        return new MongoClient(configuration.getHost(), configuration.getPort());
    }

}

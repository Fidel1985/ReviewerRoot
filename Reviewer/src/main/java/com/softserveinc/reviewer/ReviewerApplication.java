package com.softserveinc.reviewer;

import javax.servlet.DispatcherType;
import java.util.EnumSet;
import java.util.Properties;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.softserveinc.reviewer.config.DatabaseConfiguration;
import com.softserveinc.reviewer.config.PersistenceInitializer;
import com.softserveinc.reviewer.health.DataProvidersHealthCheck;
import com.softserveinc.reviewer.injector.GuiceModule;
import com.softserveinc.reviewer.injector.MyModule;
import com.softserveinc.reviewer.resources.ReviewerResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class ReviewerApplication extends Application<ReviewerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ReviewerApplication().run(args);
    }

    @Override
    public String getName() {
        return "Reviewer";
    }

    @Override
    public void run(final ReviewerConfiguration configuration, final Environment environment) {
        Injector injector = Guice.createInjector(new GuiceModule(configuration), createJpaPersistModule(configuration.getDatabase()));
        injector.getInstance(PersistenceInitializer.class);
        //Injector injector = Guice.createInjector(new GuiceModule(configuration), new MyModule());
        environment.healthChecks().register("switchboard", new DataProvidersHealthCheck(configuration.getSyndicationHealthCheckUrl()));
        environment.healthChecks().register("oracle", new DataProvidersHealthCheck(configuration.getOracleHealthCheckUrl()));
        environment.healthChecks().register("elasticsearch", new DataProvidersHealthCheck(configuration.getElasticSearchHealthCheckUrl()));
        //environment.servlets().addFilter("PersistFilter", injector.getInstance(PersistFilter.class)).
        //        addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        environment.jersey().register(injector.getInstance(ReviewerResource.class));
    }

    private JpaPersistModule createJpaPersistModule(DatabaseConfiguration conf) {
        Properties props = new Properties();
        props.put("javax.persistence.jdbc.url", conf.getUrl());
        props.put("javax.persistence.jdbc.user", conf.getUser());
        props.put("javax.persistence.jdbc.password", conf.getPassword());
        props.put("javax.persistence.jdbc.driver", conf.getDriverClass());
        //props.put("javax.persistence.jdbc.pool_size", conf.getPoolSize());
        JpaPersistModule jpaModule = new JpaPersistModule("Default");
        jpaModule.properties(props);
        return jpaModule;
    }

}

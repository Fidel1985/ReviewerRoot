package com.softserveinc.reviewer;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.softserveinc.reviewer.api.Elastic;
import com.softserveinc.reviewer.api.Oracle;
import com.softserveinc.reviewer.resources.ElasticResource;
import com.softserveinc.reviewer.resources.OracleResource;
import com.softserveinc.reviewer.resources.SwitchBoardResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ProvidersApplication extends Application<ProvidersConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ProvidersApplication().run(args);
    }

    @Override
    public String getName() {
        return "DataProviders";
    }

    @Override
    public void initialize(final Bootstrap<ProvidersConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final ProvidersConfiguration configuration,
                    final Environment environment) {
        Injector injector = Guice.createInjector();
        environment.jersey().register(injector.getInstance(SwitchBoardResource.class));
        environment.jersey().register(injector.getInstance(OracleResource.class));
        environment.jersey().register(injector.getInstance(ElasticResource.class));
    }

}

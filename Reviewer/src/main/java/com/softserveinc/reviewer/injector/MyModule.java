package com.softserveinc.reviewer.injector;

import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;

public class MyModule extends ServletModule {
    protected void configureServlets() {
        install(new JpaPersistModule("Default"));  // like we saw earlier.

        filter("/*").through(PersistFilter.class);
    }
}
package com.softserveinc.reviewer.config;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;

public class PersistenceInitializer {
    @Inject
    PersistenceInitializer(PersistService service) {
        service.start();

        // At this point JPA is started and ready.
    }
}

package com.softserveinc.reviewer.health;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

import com.codahale.metrics.health.HealthCheck;
import com.google.inject.Inject;
import com.softesrveinc.reviwer.model.Review;
import org.glassfish.jersey.client.JerseyClient;

public class ElasticSearchHealthCheck extends HealthCheck {
    private final JerseyClient client;

    @Inject
    public ElasticSearchHealthCheck(JerseyClient client) {
        this.client = client;
    }

    @Override
    protected Result check() throws Exception {
        WebTarget webTarget = client.target("http://localhost:8084/explore?type=review&client=oak-style&subjectProduct.externalId=oak-sc-2ft");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        List<Review> reviews = response.readEntity(ArrayList.class);

        if (reviews != null && reviews.size() > 0) {
            return Result.healthy();
        }
        return Result.unhealthy("ElasticSearch API Failed.");
    }
}

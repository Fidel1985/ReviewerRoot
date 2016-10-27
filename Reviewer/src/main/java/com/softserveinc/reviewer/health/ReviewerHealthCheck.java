package com.softserveinc.reviewer.health;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.health.HealthCheck;
import com.google.inject.Inject;
import com.softserveinc.reviewer.annotation.ReviewerBaseUrl;
import com.softserveinc.reviewer.annotation.ReviewerUri;
import com.softserveinc.reviewer.model.ReviewResult;
import org.glassfish.jersey.client.JerseyClient;

public class ReviewerHealthCheck extends HealthCheck {
    private static final String CLIENT_ID = "table-next";
    private static final String PRODUCT_ID = "wooden-table";

    private final JerseyClient client;
    private final String reviewerBaseUrl;
    private final String reviewerUri;

    @Inject
    public ReviewerHealthCheck(JerseyClient client, @ReviewerBaseUrl String reviewerBaseUrl, @ReviewerUri String reviewerUri) {
        this.client = client;
        this.reviewerBaseUrl = reviewerBaseUrl;
        this.reviewerUri = reviewerUri;
    }

    @Override
    protected Result check() throws Exception {
        WebTarget webTarget = client.target(reviewerBaseUrl).path(reviewerUri).path(CLIENT_ID).path(PRODUCT_ID);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        ReviewResult reviewResult = response.readEntity(ReviewResult.class);
        if(reviewResult.getOwn() > 0 && reviewResult.getSyndicated() > 0) {
            return Result.healthy();
        }
        return Result.unhealthy("Reviewer API Failed.");
    }
}
package com.softserveinc.reviewer.service;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import com.google.inject.Inject;
import com.softserveinc.reviewer.ReviewerConfiguration;
import com.softserveinc.reviewer.model.Syndication;
import com.softserveinc.reviewer.response.SyndicationResponse;
import org.glassfish.jersey.client.JerseyClient;

public class SyndicationService {
    private final ReviewerConfiguration configuration;
    private final JerseyClient client;

    @Inject
    public SyndicationService(ReviewerConfiguration configuration, JerseyClient client) {
        this.configuration = configuration;
        this.client = client;
    }

    public List<Syndication> getSources(String destinationClient) {
        WebTarget webTarget = client.target(configuration.getSyndicationBaseUrl()).path(configuration.getSyndicationUri()).path(destinationClient);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        SyndicationResponse syndicationResponse = response.readEntity(SyndicationResponse.class);
        return syndicationResponse.getData();
    }
}

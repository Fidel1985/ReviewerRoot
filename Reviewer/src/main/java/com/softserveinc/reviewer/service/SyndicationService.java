package com.softserveinc.reviewer.service;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.softserveinc.reviewer.model.Syndication;
import com.softserveinc.reviewer.response.SyndicationResponse;
import com.softserveinc.reviewer.annotation.SyndicationBaseUrl;
import com.softserveinc.reviewer.annotation.SyndicationUri;
import org.glassfish.jersey.client.JerseyClient;

public class SyndicationService {
    private final JerseyClient client;
    private final String syndicationBaseUrl;
    private final String syndicationUri;

    @Inject
    public SyndicationService(JerseyClient client, @SyndicationBaseUrl String syndicationBaseUrl, @SyndicationUri String syndicationUri) {
        this.client = client;
        this.syndicationBaseUrl = syndicationBaseUrl;
        this.syndicationUri = syndicationUri;
    }

    public List<Syndication> getSources(String destinationClient) {
        WebTarget webTarget = client.target(syndicationBaseUrl).path(syndicationUri).path(destinationClient);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        SyndicationResponse syndicationResponse = response.readEntity(SyndicationResponse.class);
        if(syndicationResponse != null && syndicationResponse.getData() != null) {
            return syndicationResponse.getData();
        }
        else {
            return new ArrayList<>();
        }
    }
}

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
import com.softserveinc.reviewer.annotation.SwitchBoardBaseUrl;
import com.softserveinc.reviewer.annotation.SwitchBoardUri;
import org.glassfish.jersey.client.JerseyClient;

public class SwitchBoardService {
    private final JerseyClient client;
    private final String switchBoardBaseUrl;
    private final String switchBoardUri;

    @Inject
    public SwitchBoardService(JerseyClient client, @SwitchBoardBaseUrl String switchBoardBaseUrl, @SwitchBoardUri String switchBoardUri) {
        this.client = client;
        this.switchBoardBaseUrl = switchBoardBaseUrl;
        this.switchBoardUri = switchBoardUri;
    }

    public List<Syndication> getSources(String destinationClient) {
        WebTarget webTarget = client.target(switchBoardBaseUrl).path(switchBoardUri).path(destinationClient);
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

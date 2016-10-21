package com.softserveinc.reviewer.service;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import com.google.inject.Inject;
import com.softserveinc.reviewer.ReviewerConfiguration;
import com.softserveinc.reviewer.model.Product;
import com.softserveinc.reviewer.response.OracleResponse;
import org.glassfish.jersey.client.JerseyClient;

public class OracleService {
    private final ReviewerConfiguration configuration;
    private final JerseyClient client;

    @Inject
    public OracleService(ReviewerConfiguration configuration, JerseyClient client) {
        this.configuration = configuration;
        this.client = client;
    }

    public List<Product> getSourceMatches(String clientId, String productId) {
        String formattedUri = String.format(configuration.getOracleUri(), clientId, productId);
        WebTarget webTarget = client.target(configuration.getOracleBaseUrl()).path(formattedUri);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        OracleResponse oracleResponse = response.readEntity(OracleResponse.class);
        return oracleResponse.getProducts();
    }
}

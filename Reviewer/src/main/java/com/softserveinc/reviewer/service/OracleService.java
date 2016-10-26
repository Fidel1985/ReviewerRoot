package com.softserveinc.reviewer.service;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.softesrveinc.reviwer.model.Product;
import com.softesrveinc.reviwer.response.OracleResponse;
import com.softserveinc.reviewer.annotation.OracleBaseUrl;
import com.softserveinc.reviewer.annotation.OracleUri;
import org.glassfish.jersey.client.JerseyClient;

public class OracleService {
    private final JerseyClient client;
    private final String oracleBaseUrl;
    private final String oracleUri;

    @Inject
    public OracleService(JerseyClient client, @OracleBaseUrl String oracleBaseUrl, @OracleUri String oracleUri) {
        this.client = client;
        this.oracleBaseUrl = oracleBaseUrl;
        this.oracleUri = oracleUri;
    }

    public List<Product> getSourceMatches(String clientId, String productId) {
        String formattedUri = String.format(oracleUri, clientId, productId);
        WebTarget webTarget = client.target(oracleBaseUrl).path(formattedUri);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        OracleResponse oracleResponse = response.readEntity(OracleResponse.class);
        if(oracleResponse != null && oracleResponse.getProducts() != null) {
            return oracleResponse.getProducts();
        }
        else {
            return new ArrayList<>();
        }
    }
}

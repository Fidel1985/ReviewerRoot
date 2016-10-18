package com.softserveinc.reviewer.api;

/**
 * entity that determines similarity between products, belongs to oracle response
 */
public class SimilarProduct {
    private String client;
    private String externalId;

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
}

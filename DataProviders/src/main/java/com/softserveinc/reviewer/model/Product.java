package com.softserveinc.reviewer.model;

/**
 * entity that determines similarity between products, belongs to oracle response
 */
public class Product {
    private String client;
    private String externalId;

    public Product(String client, String externalId) {
        this.client = client;
        this.externalId = externalId;
    }

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

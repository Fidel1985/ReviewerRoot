package com.softserveinc.reviewer.model;

/**
 * entity that determines similarity between products, belongs to oracle response
 */
public class ProductMatch {
    private String client;
    private String product;

    public ProductMatch(String client, String product) {
        this.client = client;
        this.product = product;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}

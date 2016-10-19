package com.softserveinc.reviewer.model;

public class ProductRelation {
    private String destinationClient;
    private String destinationProductId;
    private String externalId;

    public ProductRelation(String destinationClient, String destinationProductId, String externalId) {
        this.destinationClient = destinationClient;
        this.destinationProductId = destinationProductId;
        this.externalId = externalId;
    }

    public String getDestinationClient() {
        return destinationClient;
    }

    public void setDestinationClient(String destinationClient) {
        this.destinationClient = destinationClient;
    }

    public String getDestinationProductId() {
        return destinationProductId;
    }

    public void setDestinationProductId(String destinationProductId) {
        this.destinationProductId = destinationProductId;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
}

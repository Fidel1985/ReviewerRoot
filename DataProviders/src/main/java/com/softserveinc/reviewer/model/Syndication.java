package com.softserveinc.reviewer.model;

/**
 * entity that describes relation between destinationClient and sourceClient, belongs to switchboard response
 */
public class Syndication {

    private String destinationClient;
    private String sourceClient;

    public Syndication(String destinationClient, String sourceClient) {
        this.destinationClient = destinationClient;
        this.sourceClient = sourceClient;
    }

        public String getDestinationClient() {
        return destinationClient;
    }

    public void setDestinationClient(String destinationClient) {
        this.destinationClient = destinationClient;
    }

    public String getSourceClient() {
        return sourceClient;
    }

    public void setSourceClient(String sourceClient) {
        this.sourceClient = sourceClient;
    }

}
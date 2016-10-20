package com.softserveinc.reviewer.model;

public class ReviewResult {
    private String client;
    private String externalId;
    private long overall;
    private long syndicated;

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

    public long getOverall() {
        return overall;
    }

    public void setOverall(long overall) {
        this.overall = overall;
    }

    public long getSyndicated() {
        return syndicated;
    }

    public void setSyndicated(long syndicated) {
        this.syndicated = syndicated;
    }
}

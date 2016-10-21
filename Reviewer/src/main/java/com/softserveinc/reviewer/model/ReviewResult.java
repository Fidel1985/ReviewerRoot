package com.softserveinc.reviewer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReviewResult {
    private String client;
    private String externalId;
    private long overall;
    private long syndicated;

    public ReviewResult() {
    }

    public ReviewResult(String client, String externalId, long overall, long syndicated) {
        this.client = client;
        this.externalId = externalId;
        this.overall = overall;
        this.syndicated = syndicated;
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

    @JsonProperty("native")
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

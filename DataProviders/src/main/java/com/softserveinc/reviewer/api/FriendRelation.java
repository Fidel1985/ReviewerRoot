package com.softserveinc.reviewer.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * entity that describes relation between destinationClientId and sourceClientId, belongs to switchboard response
 */
public class FriendRelation {

    private String destinationClientId;
    private String sourceClientId;

    public FriendRelation(String destinationClientId, String sourceClientId) {
        this.destinationClientId = destinationClientId;
        this.sourceClientId = sourceClientId;
    }

    //@JsonProperty
    public String getDestinationClientId() {
        return destinationClientId;
    }

    public void setDestinationClientId(String destinationClientId) {
        this.destinationClientId = destinationClientId;
    }

    //@JsonProperty
    public String getSourceClientId() {
        return sourceClientId;
    }

    public void setSourceClientId(String sourceClientId) {
        this.sourceClientId = sourceClientId;
    }

}
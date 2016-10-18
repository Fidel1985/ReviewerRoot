package com.softserveinc.reviewer.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SwitchBoard {

    private long destinationClientId;
    private long sourceClientId;

    public SwitchBoard(long destinationClientId, long sourceClientId) {
        this.destinationClientId = destinationClientId;
        this.sourceClientId = sourceClientId;
    }

    @JsonProperty
    public Long getDestinationClientId() {
        return destinationClientId;
    }

    public void setDestinationClientId(long destinationClientId) {
        this.destinationClientId = destinationClientId;
    }

    @JsonProperty
    public Long getSourceClientId() {
        return sourceClientId;
    }

    public void setSourceClientId(long sourceClientId) {
        this.sourceClientId = sourceClientId;
    }

}
package com.softserveinc.reviewer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SyndicationHealth {
    @JsonProperty("SyndicationHealthCheck")
    private SyndicationHealthResponse syndicationHealthResponse;

    @Getter
    @NoArgsConstructor
    public static class SyndicationHealthResponse {
        private boolean healthy;
        private String message;
    }
}

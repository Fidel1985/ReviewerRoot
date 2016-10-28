package com.softserveinc.reviewer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ElasticSearchHealth {
    @JsonProperty("ElasticSearchHealthCheck")
    private ElasticSearchResponse elasticSearchResponse;

    @Getter
    @NoArgsConstructor
    public static class ElasticSearchResponse {
        private boolean healthy;
        private String message;
    }
}

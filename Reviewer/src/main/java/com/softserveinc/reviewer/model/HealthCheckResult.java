package com.softserveinc.reviewer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HealthCheckResult {
    @JsonProperty("ElasticSearchHealthCheck")
    private ElasticSearchHealthCheck elasticSearchHealthCheck;
    @JsonProperty("OracleHealthCheck")
    private OracleHealthCheck oracleHealthCheck;
    @JsonProperty("SyndicationHealthCheck")
    private SyndicationHealthCheck syndicationHealthCheck;

    @Getter
    @NoArgsConstructor
    public static class ElasticSearchHealthCheck {
        private boolean healthy;
        private String message;
    }
    @Getter
    @NoArgsConstructor
    public static class OracleHealthCheck {
        private boolean healthy;
        private String message;
    }
    @Getter
    @NoArgsConstructor
    public static class SyndicationHealthCheck {
        private boolean healthy;
        private String message;
    }
}

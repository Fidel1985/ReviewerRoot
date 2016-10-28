package com.softserveinc.reviewer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OracleHealth {
    @JsonProperty("OracleHealthCheck")
    private OracleHealthResponse oracleHealthResponse;

    @Getter
    @NoArgsConstructor
    public static class OracleHealthResponse {
        private boolean healthy;
        private String message;
    }
}


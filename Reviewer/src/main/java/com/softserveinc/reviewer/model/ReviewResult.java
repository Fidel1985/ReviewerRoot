package com.softserveinc.reviewer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResult {
    private String client;
    private String externalId;
    private long own;
    private long syndicated;

    @JsonProperty("native")
    public long getOwn() {
        return own;
    }

}

package com.softesrveinc.reviewer.response;

import com.softesrveinc.reviewer.model.Review;

import java.util.List;

public class ElasticSearchResponse {
    private List<Review> hits;

    public List<Review> getHits() {
        return hits;
    }

    public void setHits(List<Review> hits) {
        this.hits = hits;
    }
}

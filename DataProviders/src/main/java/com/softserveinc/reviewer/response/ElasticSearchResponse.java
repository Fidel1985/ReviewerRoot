package com.softserveinc.reviewer.response;

import com.softserveinc.reviewer.model.Review;

import java.util.List;

public class ElasticSearchResponse {
    private List<Review> reviews;

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}

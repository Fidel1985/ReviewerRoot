package com.softserveinc.reviewer.service;

import com.softserveinc.reviewer.model.ReviewResult;

public class ReviewerService {

    //jersey.restclient
    public ReviewResult getMatches(String client, String externalId) {
        ReviewResult result = new ReviewResult(client, externalId, 1L, 2L);
        return result;
    }
}

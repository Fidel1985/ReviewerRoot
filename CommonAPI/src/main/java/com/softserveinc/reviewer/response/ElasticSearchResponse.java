package com.softserveinc.reviewer.response;

import com.softserveinc.reviewer.model.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ElasticSearchResponse {
    private List<Review> hits;

}

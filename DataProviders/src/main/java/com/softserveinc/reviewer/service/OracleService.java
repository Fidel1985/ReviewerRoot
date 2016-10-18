package com.softserveinc.reviewer.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.softserveinc.reviewer.api.SimilarProduct;

public class OracleService {

    private static final List<SimilarProduct> PRODUCTS = Arrays.asList(new SimilarProduct("1", "1"), new SimilarProduct("1", "2"),
            new SimilarProduct("1", "3"), new SimilarProduct("2", "1"), new SimilarProduct("1", "2"), new SimilarProduct("3", "3"));

    public List<SimilarProduct> getSimilarProductsByIds(String client, String externalId) {
        return PRODUCTS.stream().filter(x -> x.getClient().equals(client) && x.getExternalId().equals(externalId)).
                collect(Collectors.toList());
    }
}

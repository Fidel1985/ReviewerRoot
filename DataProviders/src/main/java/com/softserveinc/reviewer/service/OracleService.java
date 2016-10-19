package com.softserveinc.reviewer.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.softserveinc.reviewer.model.ProductMatch;

public class OracleService {

    private static final List<ProductMatch> PRODUCT_MATCHES = Arrays.asList(new ProductMatch("1", "1"), new ProductMatch("1", "2"),
            new ProductMatch("1", "3"), new ProductMatch("2", "1"), new ProductMatch("1", "2"), new ProductMatch("3", "3"));

    public List<ProductMatch> getMatches(String client, String product
    ) {
        return PRODUCT_MATCHES.stream().filter(x -> x.getClient().equals(client) && x.getProduct().equals(product)).
                collect(Collectors.toList());
    }
}

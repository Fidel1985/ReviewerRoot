package com.softserveinc.reviewer.api;

import java.util.List;

public class Oracle {
    private List<SimilarProduct> products;

    public List<SimilarProduct> getProducts() {
        return products;
    }

    public void setProducts(List<SimilarProduct> products) {
        this.products = products;
    }
}

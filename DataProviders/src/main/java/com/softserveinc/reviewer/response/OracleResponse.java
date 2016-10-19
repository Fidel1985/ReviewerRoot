package com.softserveinc.reviewer.response;

import com.softserveinc.reviewer.model.ProductMatch;

import java.util.List;

public class OracleResponse {
    private List<ProductMatch> products;

    public List<ProductMatch> getProducts() {
        return products;
    }

    public void setProducts(List<ProductMatch> products) {
        this.products = products;
    }
}

package com.softserveinc.reviewer.response;

import com.softserveinc.reviewer.model.Product;

import java.util.List;

public class OracleResponse {
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

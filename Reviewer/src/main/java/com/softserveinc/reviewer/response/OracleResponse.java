package com.softserveinc.reviewer.response;

import java.util.List;

import com.softserveinc.reviewer.model.Product;

public class OracleResponse {
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

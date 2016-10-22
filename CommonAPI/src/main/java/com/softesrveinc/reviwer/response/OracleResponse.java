package com.softesrveinc.reviwer.response;

import com.softesrveinc.reviwer.model.Product;

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

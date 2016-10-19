package com.softserveinc.reviewer.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.softserveinc.reviewer.model.Product;

public class OracleService {

    private static final List<Product> CHAIR_MATCHES = Arrays.asList(new Product("oak-style", "oak-sc-2ft"),
            new Product("sitcom", "2000234"), new Product("sitcom", "1000212"), new Product("sitcom", "1000205"),
            new Product("sitcom", "1000224"), new Product("just-plastic", "1000204"));

    private static final List<Product> TABLE_MATCHES = Arrays.asList(new Product("oak-style", "oak-dwt-40inc"),
            new Product("table-next", "wooden-table"), new Product("table-next", "metal-table"), new Product("table-next", "plastic-table-rnd"),
            new Product("table-next", "plastic-table-sqr"), new Product("besttable", "plNewWood"), new Product("besttable", "roundPlastic"),
            new Product("besttable", "1000203"));

    private static final Collection<List<Product>> MATCHES = Arrays.asList(CHAIR_MATCHES, TABLE_MATCHES);

    public List<Product> getMatches(String clientId, String productId) {

        List<Product> productMatches = new ArrayList<>();

        for (List<Product> products: MATCHES) {
            if (products.stream().anyMatch(x -> x.getClient().equals(clientId) && x.getExternalId().equals(productId))){
                List<Product> productsExcludedClientItself = products.stream().filter(x -> !x.getClient().equals(clientId)).
                        collect(Collectors.toList());
                productMatches.addAll(productsExcludedClientItself);
            }
        }
        return productMatches;
    }
}

package com.softserveinc.reviewer.service;

import com.softesrveinc.reviewer.model.Product;
import com.softesrveinc.reviewer.response.OracleResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class OracleService {

    private static final Collection<List<Product>> MATCHES = Arrays.asList(
            //plastic tables(yellow)
            Arrays.asList(new Product("besttable", "roundPlastic"), new Product("table-next", "plastic-table-rnd"),
                    new Product("table-next", "plastic-table-sqr"), new Product("just-plastic", "1000203")),
            //wooden tables(blue)
            Arrays.asList(new Product("table-next", "wooden-table"), new Product("oak-style", "oak-dwt-40inc"),
                    new Product("besttable", "plNewWood")),
            //plastic chairs(green)
            Arrays.asList(new Product("sitcom", "1000224"), new Product("just-plastic", "1000204")),
            //soft chairs(red)
            Arrays.asList(new Product("oak-style", "oak-sc-2ft"), new Product("sitcom", "1000212"))
    );

    public OracleResponse getMatches(String clientId, String productId) {

        List<Product> productMatches = new ArrayList<>();
        for (List<Product> products: MATCHES) {
            if (products.stream().anyMatch(x -> x.getClient().equals(clientId) && x.getExternalId().equals(productId))){
                List<Product> productsExcludedClientItself = products.stream().filter(x -> !x.getClient().equals(clientId)).
                        collect(Collectors.toList());
                productMatches.addAll(productsExcludedClientItself);
            }
        }
        return new OracleResponse(productMatches);
    }
}

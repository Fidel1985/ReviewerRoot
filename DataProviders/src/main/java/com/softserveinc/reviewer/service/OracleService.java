package com.softserveinc.reviewer.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.softserveinc.reviewer.model.ProductMatch;
import com.softserveinc.reviewer.model.ProductRelation;

public class OracleService {

    private static final List<ProductRelation> PRODUCT_RELATIONS = Arrays.asList(new ProductRelation("oak-style", "oak-sc-2ft", "1000212"),
            new ProductRelation("oak-style", "oak-sc-2ft", "1000205"), new ProductRelation("oak-style", "oak-sc-2ft", "2000234"),
            new ProductRelation("oak-style", "oak-dwt-40inc", "wooden-table"), new ProductRelation("oak-style", "oak-dwt-40inc", "metal-table"),
            new ProductRelation("oak-style", "oak-dwt-40inc", "plastic-table-rnd"),
            new ProductRelation("oak-style", "oak-dwt-40inc", "plastic-table-sqr"),
            new ProductRelation("oak-style", "oak-dwt-40inc", "plNewWood"), new ProductRelation("oak-style", "oak-dwt-40inc", "roundPlastic"));

    private static final List<ProductMatch> PRODUCT_MATCHES = Arrays.asList(new ProductMatch("oak-style", "oak-sc-2ft"),
            new ProductMatch("oak-style", "oak-dwt-40inc"), new ProductMatch("oak-style", "oak-chrl-10lbs"),
            new ProductMatch("table-next", "wooden-table"), new ProductMatch("table-next", "metal-table"),
            new ProductMatch("table-next", "plastic-table-rnd"), new ProductMatch("table-next", "plastic-table-sqr"),
            new ProductMatch("sitcom", "2000234"), new ProductMatch("sitcom", "1000212"), new ProductMatch("sitcom", "1000205"),
            new ProductMatch("besttable", "plNewWood"), new ProductMatch("besttable", "roundPlastic"));

    public List<ProductMatch> getMatches(String clientId, String productId) {

        List<String> externalIds = PRODUCT_RELATIONS.stream().filter(x -> x.getDestinationClient().equals(clientId) &&
                x.getDestinationProductId().equals(productId)).map(ProductRelation::getExternalId).collect(Collectors.toList());

        return PRODUCT_MATCHES.stream().filter(x -> externalIds.contains(x.getExternalId())).collect(Collectors.toList());
    }
}

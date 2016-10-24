package com.softserveinc.reviewer.service;

import com.softesrveinc.reviewer.model.Syndication;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SyndicationService {

    private static final List<Syndication> SYNDICATIONS = Arrays.asList(
            new Syndication("table-next", "besttable"), new Syndication("table-next", "oak-style"),
            new Syndication("besttable", "table-next"),
            new Syndication("oak-style", "sitcom"), new Syndication("oak-style", "besttable"),
            new Syndication("sitcom", "just-plastic"),
            new Syndication("just-plastic", "sitcom")
            );

    public List<Syndication> getSources(String destinationClient) {
        return SYNDICATIONS.stream().filter(x -> x.getDestinationClient().equals(destinationClient)).collect(Collectors.toList());
    }
}

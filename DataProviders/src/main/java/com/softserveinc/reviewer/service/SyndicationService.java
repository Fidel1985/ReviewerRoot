package com.softserveinc.reviewer.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.softserveinc.reviewer.model.Syndication;

public class SyndicationService {

    private static final List<Syndication> SYNDICATIONS = Arrays.asList(new Syndication("oak-style", "table-next"),
            new Syndication("oak-style", "table-next"), new Syndication("table-next", "besttable"), new Syndication("sitcom", "oak-style"),
            new Syndication("besttable", "table-next"));

    public List<Syndication> getSources(String destinationClient) {
        return SYNDICATIONS.stream().filter(x -> x.getDestinationClient().equals(destinationClient)).collect(Collectors.toList());
    }
}

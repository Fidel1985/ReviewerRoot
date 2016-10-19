package com.softserveinc.reviewer.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.softserveinc.reviewer.model.Syndication;

public class SyndicationService {

    private static final List<Syndication> SYNDICATIONS = Arrays.asList(new Syndication("1", "1"), new Syndication("1", "2"), new Syndication("1", "3"),
            new Syndication("2", "1"), new Syndication("2", "3"), new Syndication("3", "2"));

    public List<Syndication> getSources(String clientId) {
        return SYNDICATIONS.stream().filter(x -> x.getDestinationClient().equals(clientId)).collect(Collectors.toList());
    }
}

package com.softserveinc.reviewer.config;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class MongoConfiguration {

    @NotNull
    private String host;
    @NotNull
    private int port;
    @NotNull
    private String database;
    @NotNull
    private String statsCollection;

}

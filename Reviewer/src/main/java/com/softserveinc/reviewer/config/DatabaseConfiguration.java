package com.softserveinc.reviewer.config;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class DatabaseConfiguration {

    @NotNull
    private String host;
    @NotNull
    private int port;
    @NotNull
    private String name;
    @NotNull
    private String collection;

}

package com.softserveinc.reviewer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongojack.ObjectId;

public class Statistic {
    @ObjectId
    private String id;
    private String name;

    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

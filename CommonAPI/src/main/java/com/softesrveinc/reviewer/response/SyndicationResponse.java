package com.softesrveinc.reviewer.response;

import com.softesrveinc.reviewer.model.Syndication;

import java.util.List;

public class SyndicationResponse {
    private List<Syndication> data;

    public List<Syndication> getData() {
        return data;
    }

    public void setData(List<Syndication> data) {
        this.data = data;
    }
}
package com.softserveinc.reviewer.response;

import java.util.List;

import com.softserveinc.reviewer.model.Syndication;

public class SyndicationResponse {
    private List<Syndication> data;

    public List<Syndication> getData() {
        return data;
    }

    public void setData(List<Syndication> data) {
        this.data = data;
    }
}
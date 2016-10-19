package com.softserveinc.reviewer.response;

import com.softserveinc.reviewer.model.Syndication;

import java.util.List;

public class SwitchBoardResponse {
    private List<Syndication> data;

    public List<Syndication> getData() {
        return data;
    }

    public void setData(List<Syndication> data) {
        this.data = data;
    }
}
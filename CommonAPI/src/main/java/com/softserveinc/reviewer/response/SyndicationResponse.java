package com.softserveinc.reviewer.response;

import com.softserveinc.reviewer.model.Syndication;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SyndicationResponse {
    private List<Syndication> data;

}
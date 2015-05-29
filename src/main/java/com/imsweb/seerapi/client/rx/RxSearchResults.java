/*
 * Copyright (C) 2011 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.rx;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.imsweb.seerapi.client.publishable.PublishableSearchResults;

public class RxSearchResults extends PublishableSearchResults {

    @JsonProperty("results")
    protected List<Rx> _results;

    public List<Rx> getResults() {
        return _results;
    }

    public void setResults(List<Rx> results) {
        _results = results;
    }
}

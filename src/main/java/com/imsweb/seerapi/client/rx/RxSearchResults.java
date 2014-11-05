/*
 * Copyright (C) 2011 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.rx;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.imsweb.seerapi.client.publishable.PublishableSearchResults;

public class RxSearchResults extends PublishableSearchResults {

    @JsonProperty("results")
    protected List<Rx> _results;

    public List<Rx> getResults() {
        return _results;
    }
}

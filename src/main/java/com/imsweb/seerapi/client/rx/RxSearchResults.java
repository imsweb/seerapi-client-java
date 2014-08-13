/*
 * Copyright (C) 2011 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.rx;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class RxSearchResults {

    @JsonProperty("count")
    protected Integer _count;
    @JsonProperty("results")
    protected List<Rx> _results;
    @JsonProperty("terms")
    protected List<String> _terms;

    public List<String> getTerms() {
        return _terms;
    }

    public Integer getCount() {
        return _count;
    }

    public List<Rx> getResults() {
        return _results;
    }
}

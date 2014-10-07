/*
 * Copyright (C) 2011 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.publishable;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class PublishableSearchResults {

    @JsonProperty("terms")
    protected List<String> _terms;
    @JsonProperty("count")
    protected Integer _count;
    @JsonProperty("offset")
    protected Integer _offset;
    @JsonProperty("max_score")
    protected Integer _maxScore;

    public List<String> getTerms() {
        return _terms;
    }

    public Integer getCount() {
        return _count;
    }

    public Integer getOffset() {
        return _offset;
    }

    public Integer getMaxScore() {
        return _maxScore;
    }
}

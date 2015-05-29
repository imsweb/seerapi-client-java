/*
 * Copyright (C) 2011 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.publishable;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PublishableSearchResults {

    @JsonProperty("terms")
    protected List<String> _terms;
    @JsonProperty("total")
    protected Integer _total;
    @JsonProperty("count")
    protected Integer _count;
    @JsonProperty("offset")
    protected Integer _offset;
    @JsonProperty("max_score")
    protected Integer _maxScore;
    @JsonProperty("order")
    protected String _order;

    public List<String> getTerms() {
        return _terms;
    }

    public Integer getTotal() {
        return _total;
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

    public String getOrder() {
        return _order;
    }

    public void setTerms(List<String> terms) {
        _terms = terms;
    }

    public void setTotal(Integer total) {
        _total = total;
    }

    public void setCount(Integer count) {
        _count = count;
    }

    public void setOffset(Integer offset) {
        _offset = offset;
    }

    public void setMaxScore(Integer maxScore) {
        _maxScore = maxScore;
    }

    public void setOrder(String order) {
        _order = order;
    }
}

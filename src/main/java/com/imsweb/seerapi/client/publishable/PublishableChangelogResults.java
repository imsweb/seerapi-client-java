/*
 * Copyright (C) 2011 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.publishable;

import org.codehaus.jackson.annotate.JsonProperty;

public class PublishableChangelogResults {

    protected Long _total;
    protected Integer _count;
    protected Integer _offset;

    /**
     * Default constructor
     */
    public PublishableChangelogResults() {
    }

    @JsonProperty("total")
    public Long getTotal() {
        return _total;
    }

    public void setTotal(Long total) {
        _total = total;
    }

    @JsonProperty("count")
    public Integer getCount() {
        return _count;
    }

    public void setCount(Integer count) {
        _count = count;
    }

    @JsonProperty("offset")
    public Integer getOffset() {
        return _offset;
    }

    public void setOffset(Integer offset) {
        _offset = offset;
    }
}

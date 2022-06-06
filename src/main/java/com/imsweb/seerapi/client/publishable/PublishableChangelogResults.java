/*
 * Copyright (C) 2011 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.publishable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PublishableChangelogResults {

    protected Long _total;
    protected Integer _count;
    protected Integer _offset;

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

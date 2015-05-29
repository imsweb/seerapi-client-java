/*
 * Copyright (C) 2012 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.publishable;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PublishableVersion {

    @JsonProperty("version")
    private String _name;
    @JsonProperty("type")
    private String _type;
    @JsonProperty("first_published")
    private Date _firstPublished;
    @JsonProperty("last_modified")
    private Date _lastModified;
    @JsonProperty("count")
    private Long _count;

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getType() {
        return _type;
    }

    public void setType(String type) {
        _type = type;
    }

    public Date getFirstPublished() {
        return _firstPublished;
    }

    public void setFirstPublished(Date firstPublished) {
        _firstPublished = firstPublished;
    }

    public Date getLastModified() {
        return _lastModified;
    }

    public void setLastModified(Date lastModified) {
        _lastModified = lastModified;
    }

    public Long getCount() {
        return _count;
    }

    public void setCount(Long count) {
        _count = count;
    }
}

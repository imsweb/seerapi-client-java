/*
 * Copyright (C) 2012 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.disease;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class DiseaseVersionBean {

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

    public String getType() {
        return _type;
    }

    public Date getFirstPublished() {
        return _firstPublished;
    }

    public Date getLastModified() {
        return _lastModified;
    }

    public Long getCount() {
        return _count;
    }
}

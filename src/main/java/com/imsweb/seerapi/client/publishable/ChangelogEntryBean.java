/*
 * Copyright (C) 2012 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.publishable;

import org.codehaus.jackson.annotate.JsonProperty;

public class ChangelogEntryBean {

    @JsonProperty("id")
    private String _id;
    @JsonProperty("name")
    private String _name;
    @JsonProperty("old")
    private PublishableBean _oldVersion;
    @JsonProperty("new")
    private PublishableBean _newVersion;

    public String getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public PublishableBean getOldVersion() {
        return _oldVersion;
    }

    public PublishableBean getNewVersion() {
        return _newVersion;
    }
}

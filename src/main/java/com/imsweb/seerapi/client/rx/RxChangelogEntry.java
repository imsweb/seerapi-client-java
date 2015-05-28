/*
 * Copyright (C) 2012 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.rx;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RxChangelogEntry {

    @JsonProperty("id")
    private String _id;
    @JsonProperty("name")
    private String _name;
    @JsonProperty("old")
    private Rx _oldVersion;
    @JsonProperty("new")
    private Rx _newVersion;

    public String getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public Rx getOldVersion() {
        return _oldVersion;
    }

    public Rx getNewVersion() {
        return _newVersion;
    }
}

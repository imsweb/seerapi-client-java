/*
 * Copyright (C) 2012 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.rx;

import org.codehaus.jackson.annotate.JsonProperty;

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

    public void setId(String id) {
        _id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public Rx getOldVersion() {
        return _oldVersion;
    }

    public void setOldVersion(Rx oldVersion) {
        _oldVersion = oldVersion;
    }

    public Rx getNewVersion() {
        return _newVersion;
    }

    public void setNewVersion(Rx newVersion) {
        _newVersion = newVersion;
    }
}

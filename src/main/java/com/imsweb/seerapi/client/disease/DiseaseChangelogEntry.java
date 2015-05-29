/*
 * Copyright (C) 2012 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.disease;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DiseaseChangelogEntry {

    @JsonProperty("id")
    private String _id;
    @JsonProperty("name")
    private String _name;
    @JsonProperty("old")
    private Disease _oldVersion;
    @JsonProperty("new")
    private Disease _newVersion;

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

    public Disease getOldVersion() {
        return _oldVersion;
    }

    public void setOldVersion(Disease oldVersion) {
        _oldVersion = oldVersion;
    }

    public Disease getNewVersion() {
        return _newVersion;
    }

    public void setNewVersion(Disease newVersion) {
        _newVersion = newVersion;
    }
}

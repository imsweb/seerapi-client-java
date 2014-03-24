/*
 * Copyright (C) 2012 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.disease;

import org.codehaus.jackson.annotate.JsonProperty;

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

    public String getName() {
        return _name;
    }

    public Disease getOldVersion() {
        return _oldVersion;
    }

    public Disease getNewVersion() {
        return _newVersion;
    }
}

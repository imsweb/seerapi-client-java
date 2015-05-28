/*
 * Copyright (C) 2012 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.glossary;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GlossaryChangelogEntry {

    @JsonProperty("id")
    private String _id;
    @JsonProperty("name")
    private String _name;
    @JsonProperty("old")
    private Glossary _oldVersion;
    @JsonProperty("new")
    private Glossary _newVersion;

    public String getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public Glossary getOldVersion() {
        return _oldVersion;
    }

    public Glossary getNewVersion() {
        return _newVersion;
    }
}

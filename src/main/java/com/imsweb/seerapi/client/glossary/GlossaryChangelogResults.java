/*
 * Copyright (C) 2011 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.glossary;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.imsweb.seerapi.client.publishable.PublishableChangelogResults;

public class GlossaryChangelogResults extends PublishableChangelogResults {

    protected List<GlossaryChangelog> _changelogs;

    @JsonProperty("changelogs")
    public List<GlossaryChangelog> getChangelogs() {
        return _changelogs;
    }

    public void setChangelogs(List<GlossaryChangelog> changelogs) {
        _changelogs = changelogs;
    }
}

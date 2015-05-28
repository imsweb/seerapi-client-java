/*
 * Copyright (C) 2011 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.rx;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.imsweb.seerapi.client.publishable.PublishableChangelogResults;

public class RxChangelogResults extends PublishableChangelogResults {

    protected List<RxChangelog> _changelogs;

    /**
     * Default constructor
     */
    public RxChangelogResults() {
    }

    @JsonProperty("changelogs")
    public List<RxChangelog> getChangelogs() {
        return _changelogs;
    }

    public void setChangelogs(List<RxChangelog> changelogs) {
        _changelogs = changelogs;
    }
}

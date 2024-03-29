/*
 * Copyright (C) 2011 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.disease;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.imsweb.seerapi.client.publishable.PublishableChangelogResults;

public class DiseaseChangelogResults extends PublishableChangelogResults {

    protected List<DiseaseChangelog> _changelogs;

    @JsonProperty("changelogs")
    public List<DiseaseChangelog> getChangelogs() {
        return _changelogs;
    }

    public void setChangelogs(List<DiseaseChangelog> changelogs) {
        _changelogs = changelogs;
    }
}

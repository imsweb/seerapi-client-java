/*
 * Copyright (C) 2011 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.disease;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.imsweb.seerapi.client.publishable.PublishableSearchResults;

public class DiseaseSearchResults extends PublishableSearchResults {

    @JsonProperty("results")
    protected List<Disease> _results;

    public List<Disease> getResults() {
        return _results;
    }
}

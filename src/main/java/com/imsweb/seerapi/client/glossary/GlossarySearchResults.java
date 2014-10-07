/*
 * Copyright (C) 2011 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.glossary;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.imsweb.seerapi.client.publishable.PublishableSearchResults;

public class GlossarySearchResults extends PublishableSearchResults {

    @JsonProperty("results")
    protected List<Glossary> _results;

    public List<Glossary> getResults() {
        return _results;
    }
}

/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.publishable;

import java.util.Set;

import org.codehaus.jackson.annotate.JsonProperty;

public class KeywordMatch {

    @JsonProperty("id")
    private String _id;
    @JsonProperty("keyword")
    private String _keyword;
    @JsonProperty("category")
    private Set<String> _category;

    public String getId() {
        return _id;
    }

    public String getKeyword() {
        return _keyword;
    }

    public Set<String> getCategory() {
        return _category;
    }
}

/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.shared;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    public void setId(String id) {
        _id = id;
    }

    public String getKeyword() {
        return _keyword;
    }

    public void setKeyword(String keyword) {
        _keyword = keyword;
    }

    public Set<String> getCategory() {
        return _category;
    }

    public void setCategory(Set<String> category) {
        _category = category;
    }
}

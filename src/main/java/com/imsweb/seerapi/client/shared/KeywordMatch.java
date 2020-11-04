/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.shared;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KeywordMatch {

    @JsonProperty("id")
    private String _id;
    @JsonProperty("name")
    private String _name;
    @JsonProperty("keyword")
    private String _keyword;
    @JsonProperty("category")
    private Set<String> _category;
    @JsonProperty("start")
    private Integer _start;
    @JsonProperty("end")
    private Integer _end;

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

    public Integer getStart() {
        return _start;
    }

    public void setStart(Integer start) {
        _start = start;
    }

    public Integer getEnd() {
        return _end;
    }

    public void setEnd(Integer end) {
        _end = end;
    }
}

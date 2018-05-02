/*
 * Copyright (C) 2018 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.disease;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name", "section", "pages"})
public class DiseaseSource {

    @JsonProperty("name")
    private String _name;
    @JsonProperty("section")
    private String _section;
    @JsonProperty("pages")
    private String _pages;

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getSection() {
        return _section;
    }

    public void setSection(String section) {
        _section = section;
    }

    public String getPages() {
        return _pages;
    }

    public void setPages(String pages) {
        _pages = pages;
    }
}

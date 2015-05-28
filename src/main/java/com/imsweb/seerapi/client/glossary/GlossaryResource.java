/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.glossary;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name", "url", "edition", "year", "page"})
public class GlossaryResource {

    private String _name;
    private String _url;
    private String _page;
    private String _edition;
    private String _year;

    public GlossaryResource() {
    }

    public GlossaryResource(String name) {
        _name = name;
    }

    @JsonProperty("name")
    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    @JsonProperty("url")
    public String getUrl() {
        return _url;
    }

    public void setUrl(String url) {
        _url = url;
    }

    @JsonProperty("page")
    public String getPage() {
        return _page;
    }

    public void setPage(String page) {
        _page = page;
    }

    @JsonProperty("edition")
    public String getEdition() {
        return _edition;
    }

    public void setEdition(String edition) {
        _edition = edition;
    }

    @JsonProperty("year")
    public String getYear() {
        return _year;
    }

    public void setYear(String year) {
        _year = year;
    }

}

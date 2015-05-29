/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.glossary;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name", "url", "edition", "year", "page"})
public class GlossaryResource {

    @JsonProperty("name")
    private String _name;
    @JsonProperty("url")
    private String _url;
    @JsonProperty("page")
    private String _page;
    @JsonProperty("edition")
    private String _edition;
    @JsonProperty("year")
    private String _year;

    public GlossaryResource() {
    }

    public GlossaryResource(String name) {
        _name = name;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getUrl() {
        return _url;
    }

    public void setUrl(String url) {
        _url = url;
    }

    public String getPage() {
        return _page;
    }

    public void setPage(String page) {
        _page = page;
    }

    public String getEdition() {
        return _edition;
    }

    public void setEdition(String edition) {
        _edition = edition;
    }

    public String getYear() {
        return _year;
    }

    public void setYear(String year) {
        _year = year;
    }

}

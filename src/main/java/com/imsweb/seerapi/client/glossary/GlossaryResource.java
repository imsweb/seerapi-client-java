/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.glossary;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonPropertyOrder({"url", "edition", "year", "page"})
public class GlossaryResource {

    private String _page;
    private String _edition;
    private String _year;
    private String _url;

    public GlossaryResource() {
    }

    public GlossaryResource(String page, String edition, String year, String url) {
        _page = page;
        _edition = edition;
        _year = year;
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

    @JsonProperty("url")
    public String getUrl() {
        return _url;
    }

    public void setUrl(String url) {
        _url = url;
    }
}

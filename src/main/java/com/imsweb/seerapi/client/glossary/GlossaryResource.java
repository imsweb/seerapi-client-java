/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.glossary;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name", "subtitle", "reference_type", "url", "edition", "year", "page", "citation"})
public class GlossaryResource {

    @JsonProperty("name")
    private String _name;
    @JsonProperty("page")
    private String _page;
    @JsonProperty("edition")
    private String _edition;
    @JsonProperty("year")
    private String _year;
    @JsonProperty("url")
    private String _url;
    @JsonProperty("citation")
    private String _citation;
    @JsonProperty("subtitle")
    private String _subtitle;
    @JsonProperty("reference_type")
    private String _referenceType;

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

    public String getUrl() {
        return _url;
    }

    public void setUrl(String url) {
        _url = url;
    }

    public String getCitation() {
        return _citation;
    }

    public void setCitation(String citation) {
        _citation = citation;
    }

    public String getSubtitle() {
        return _subtitle;
    }

    public void setSubtitle(String subtitle) {
        _subtitle = subtitle;
    }

    public String getReferenceType() {
        return _referenceType;
    }

    public void setReferenceType(String referenceType) {
        _referenceType = referenceType;
    }
}


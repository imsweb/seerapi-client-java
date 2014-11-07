/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class StagingSchemaSelection {

    private String _site;
    private List<StagingStringRange> _parsedSite = new ArrayList<>();
    private String _hist;
    private List<StagingStringRange> _parsedHist = new ArrayList<>();
    private String _ssf25;
    private List<StagingStringRange> _parsedSsf25 = new ArrayList<>();

    public StagingSchemaSelection() {
    }

    @JsonProperty("site")
    public String getSite() {
        return _site;
    }

    public void setSite(String site) {
        _site = site;
    }

    @JsonProperty("hist")
    public String getHist() {
        return _hist;
    }

    public void setHist(String hist) {
        _hist = hist;
    }

    @JsonProperty("ssf25")
    public String getSsf25() {
        return _ssf25;
    }

    public void setSsf25(String ssf25) {
        _ssf25 = ssf25;
    }

    @JsonIgnore
    public List<StagingStringRange> getParsedSite() {
        return _parsedSite;
    }

    public void setParsedSite(List<StagingStringRange> parsedSite) {
        _parsedSite = parsedSite;
    }

    @JsonIgnore
    public List<StagingStringRange> getParsedHist() {
        return _parsedHist;
    }

    public void setParsedHist(List<StagingStringRange> parsedHist) {
        _parsedHist = parsedHist;
    }

    @JsonIgnore
    public List<StagingStringRange> getParsedSsf25() {
        return _parsedSsf25;
    }

    public void setParsedSsf25(List<StagingStringRange> parsedSsf25) {
        _parsedSsf25 = parsedSsf25;
    }
}

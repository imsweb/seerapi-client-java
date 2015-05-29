/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.surgery;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SurgeryTable {

    @JsonProperty("title")
    protected String _title;
    @JsonProperty("site_inclusions")
    protected String _siteInclusions;
    @JsonProperty("hist_exclusions")
    protected String _histExclusions;
    @JsonProperty("hist_inclusions")
    protected String _histInclusions;
    @JsonProperty("pre_note")
    protected String _preNote;
    @JsonProperty("post_note")
    protected String _postNote;
    @JsonProperty("row")
    protected List<SurgeryRow> _rows;

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getSiteInclusions() {
        return _siteInclusions;
    }

    public void setSiteInclusions(String siteInclusions) {
        _siteInclusions = siteInclusions;
    }

    public String getHistExclusions() {
        return _histExclusions;
    }

    public void setHistExclusions(String histExclusions) {
        _histExclusions = histExclusions;
    }

    public String getHistInclusions() {
        return _histInclusions;
    }

    public void setHistInclusions(String histInclusions) {
        _histInclusions = histInclusions;
    }

    public String getPreNote() {
        return _preNote;
    }

    public void setPreNote(String preNote) {
        _preNote = preNote;
    }

    public String getPostNote() {
        return _postNote;
    }

    public void setPostNote(String postNote) {
        _postNote = postNote;
    }

    public List<SurgeryRow> getRows() {
        return _rows;
    }

    public void setRows(List<SurgeryRow> rows) {
        _rows = rows;
    }
}

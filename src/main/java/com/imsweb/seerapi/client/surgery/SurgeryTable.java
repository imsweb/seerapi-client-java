/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.surgery;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

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

    public String getSiteInclusions() {
        return _siteInclusions;
    }

    public String getHistExclusions() {
        return _histExclusions;
    }

    public String getHistInclusions() {
        return _histInclusions;
    }

    public String getPreNote() {
        return _preNote;
    }

    public String getPostNote() {
        return _postNote;
    }

    public List<SurgeryRow> getRows() {
        return _rows;
    }
}

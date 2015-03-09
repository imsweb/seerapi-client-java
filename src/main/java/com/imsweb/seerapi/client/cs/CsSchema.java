/*
 * Copyright (C) 2011 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.cs;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Simple Java object that contains all of the schemas relevant information.
 */
public class CsSchema {

    @JsonProperty("schema_number")
    protected int _schemaNumber;
    @JsonProperty("name")
    protected String _name;
    @JsonProperty("title")
    protected String _title;
    @JsonProperty("subtitle")
    protected String _subtitle;
    @JsonProperty("site_summary")
    protected String _siteSummary;
    @JsonProperty("note")
    protected List<String> _notes;
    @JsonProperty("ssf")
    protected List<CsSiteSpecificFactor> _siteSpecificFactors = new ArrayList<CsSiteSpecificFactor>();
    @JsonProperty("num_tables")
    protected int _numTables;
    @JsonProperty("revision_date")
    protected String _revisionDate;

    public int getSchemaNumber() {
        return _schemaNumber;
    }

    public String getName() {
        return _name;
    }

    public String getTitle() {
        return _title;
    }

    public String getSubtitle() {
        return _subtitle;
    }

    public String getSiteSummary() {
        return _siteSummary;
    }

    public List<String> getNotes() {
        return _notes;
    }

    public List<CsSiteSpecificFactor> getSiteSpecificFactors() {
        return _siteSpecificFactors;
    }

    public int getNumTables() {
        return _numTables;
    }

    public String getRevisionDate() {
        return _revisionDate;
    }
}
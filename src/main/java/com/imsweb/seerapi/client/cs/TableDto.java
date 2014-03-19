/*
 * Copyright (C) 2012 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.cs;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Object that contains all of the relevant information for a CStage table.
 */
public class TableDto {

    @JsonProperty("table_id")
    protected String _tableId;
    @JsonProperty("table_number")
    protected Integer _tableNumber;
    @JsonProperty("title")
    protected String _title;
    @JsonProperty("subtitle")
    protected String _subtitle;
    @JsonProperty("note")
    protected List<String> _notes;
    @JsonProperty("pattern")
    protected String _pattern;
    @JsonProperty("header")
    protected List<String> _headers;
    @JsonProperty("row")
    protected List<TableRowDto> _rows;
    @JsonProperty("footnote")
    protected List<String> _footnotes;
    @JsonProperty("usage")
    protected String _usage;
    @JsonProperty("currency")
    protected String _currency;
    @JsonProperty("role")
    protected String _role;
    @JsonProperty("revision_date")
    protected String _revisionDate;

    public String getTableId() {
        return _tableId;
    }

    public Integer getTableNumber() {
        return _tableNumber;
    }

    public String getTitle() {
        return _title;
    }

    public String getSubtitle() {
        return _subtitle;
    }

    public List<String> getNotes() {
        return _notes;
    }

    public String getPattern() {
        return _pattern;
    }

    public List<String> getHeaders() {
        return _headers;
    }

    public List<TableRowDto> getRows() {
        return _rows;
    }

    public List<String> getFootnotes() {
        return _footnotes;
    }

    public String getUsage() {
        return _usage;
    }

    public String getCurrency() {
        return _currency;
    }

    public String getRole() {
        return _role;
    }

    public String getRevisionDate() {
        return _revisionDate;
    }
}
/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "algorithm", "version", "name", "title", "subtitle", "description", "notes", "footnotes", "last_modified", "definition", "extra_input", "rows"})
public class StagingTable {

    private String _displayId;
    private String _algorithm;
    private String _version;
    private String _name;
    private String _title;
    private String _description;
    private String _subtitle;
    private String _notes;
    private String _footnotes;
    private String _rationale;
    private Date _lastModified;
    private List<StagingColumnDefinition> _definition;
    private Set<String> _extraInput;
    private List<List<String>> _rows;

    @JsonProperty("id")
    public String getId() {
        return _displayId;
    }

    public void setId(String id) {
        _displayId = id;
    }

    @JsonProperty("algorithm")
    public String getAlgorithm() {
        return _algorithm;
    }

    public void setAlgorithm(String algorithm) {
        _algorithm = algorithm;
    }

    @JsonProperty("version")
    public String getVersion() {
        return _version;
    }

    public void setVersion(String version) {
        _version = version;
    }

    @JsonProperty("name")
    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    @JsonProperty("title")
    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    @JsonProperty("description")
    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    @JsonProperty("subtitle")
    public String getSubtitle() {
        return _subtitle;
    }

    public void setSubtitle(String subtitle) {
        _subtitle = subtitle;
    }

    @JsonProperty("notes")
    public String getNotes() {
        return _notes;
    }

    public void setNotes(String notes) {
        _notes = notes;
    }

    @JsonProperty("footnotes")
    public String getFootnotes() {
        return _footnotes;
    }

    public void setFootnotes(String footnotes) {
        _footnotes = footnotes;
    }

    @JsonProperty("rationale")
    public String getRationale() {
        return _rationale;
    }

    public void setRationale(String rationale) {
        _rationale = rationale;
    }

    @JsonProperty("last_modified")
    public Date getLastModified() {
        return _lastModified;
    }

    public void setLastModified(Date lastModified) {
        _lastModified = lastModified;
    }

    @JsonProperty("definition")
    public List<StagingColumnDefinition> getColumnDefinitions() {
        return _definition;
    }

    public void setColumnDefinitions(List<StagingColumnDefinition> definition) {
        _definition = definition;
    }

    @JsonProperty("extra_input")
    public Set<String> getExtraInput() {
        return _extraInput;
    }

    public void setExtraInput(Set<String> extraInput) {
        _extraInput = extraInput;
    }

    @JsonProperty("rows")
    public List<List<String>> getRawRows() {
        return _rows;
    }

    public void setRawRows(List<List<String>> rows) {
        _rows = rows;
    }

}

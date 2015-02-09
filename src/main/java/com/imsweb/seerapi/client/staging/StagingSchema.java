/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class StagingSchema {

    private String _displayId;
    private String _algorithm;
    private String _version;
    private String _name;
    private String _title;
    private String _description;
    private String _subtitle;
    private String _notes;
    private Date _lastModified;
    private Integer _schemaNum;
    private String _schemaSelectionTable;
    private Set<String> _schemaDiscriminators;
    private List<StagingSchemaInput> _inputs;
    private List<StagingKeyValue> _initialContext;
    private List<StagingMapping> _mappings;
    Set<String> _involvedTables;

    /**
     * Morphia requires a default constructor
     */
    public StagingSchema() {
    }

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

    @JsonProperty("last_modified")
    public Date getLastModified() {
        return _lastModified;
    }

    public void setLastModified(Date lastModified) {
        _lastModified = lastModified;
    }

    @JsonProperty("schema_num")
    public Integer getSchemaNum() {
        return _schemaNum;
    }

    public void setSchemaNum(Integer schemaNum) {
        _schemaNum = schemaNum;
    }

    @JsonProperty("schema_selection_table")
    public String getSchemaSelectionTable() {
        return _schemaSelectionTable;
    }

    public void setSchemaSelectionTable(String schemaSelectionTable) {
        _schemaSelectionTable = schemaSelectionTable;
    }

    @JsonProperty("schema_discriminators")
    public Set<String> getSchemaDiscriminators() {
        return _schemaDiscriminators;
    }

    public void setSchemaDiscriminators(Set<String> schemaDiscriminators) {
        _schemaDiscriminators = schemaDiscriminators;
    }

    @JsonProperty("inputs")
    public List<StagingSchemaInput> getInputs() {
        return _inputs;
    }

    public void setInputs(List<StagingSchemaInput> inputs) {
        _inputs = inputs;
    }

    @JsonProperty("initial_context")
    public List<StagingKeyValue> getInitialContext() {
        return _initialContext;
    }

    public void setInitialContext(List<StagingKeyValue> initialContext) {
        _initialContext = initialContext;
    }

    @JsonProperty("mappings")
    public List<StagingMapping> getMappings() {
        return _mappings;
    }

    public void setMappings(List<StagingMapping> mapping) {
        _mappings = mapping;
    }

    @JsonIgnore
    public Map<String, StagingSchemaInput> getInputMap() {
        return null;
    }

    @JsonProperty("involved_tables")
    public Set<String> getInvolvedTables() {
        return _involvedTables;
    }

    public void setInvolvedTables(Set<String> involvedTables) {
        _involvedTables = involvedTables;
    }
}

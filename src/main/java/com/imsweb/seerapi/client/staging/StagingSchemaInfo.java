/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class StagingSchemaInfo {

    private String _id;
    private String _name;
    private String _title;
    private String _discriminatorTable;
    private List<StagingSchemaSelection> _schemaSelection = new ArrayList<>();
    private List<String> _mappings = new ArrayList<>();

    public StagingSchemaInfo() {
    }

    public StagingSchemaInfo(String id, String name, String title) {
        setId(id);
        setName(name);
        setTitle(title);
    }

    @JsonProperty("id")
    public String getId() {
        return _id;
    }

    public void setId(String id) {
        _id = id;
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

    @JsonProperty("discriminator_table")
    public String getDiscriminatorTable() {
        return _discriminatorTable;
    }

    public void setDiscriminatorTable(String discriminatorTable) {
        _discriminatorTable = discriminatorTable;
    }

    @JsonProperty("schema_selection")
    public List<StagingSchemaSelection> getSchemaSelection() {
        return _schemaSelection;
    }

    public void setSchemaSelection(List<StagingSchemaSelection> schemaSelection) {
        _schemaSelection = schemaSelection;
    }

    @JsonProperty("mappings")
    public List<String> getMappings() {
        return _mappings;
    }

    public void setMappings(List<String> algorithms) {
        _mappings = algorithms;
    }
}

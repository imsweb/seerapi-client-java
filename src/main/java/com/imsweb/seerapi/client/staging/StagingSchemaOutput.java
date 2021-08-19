/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"key", "name", "description", "naaccr_item", "naaccr_xml_id", "table", "default", "metadata"})
public class StagingSchemaOutput {

    private String _key;
    private String _name;
    private String _description;
    private Integer _naaccrItem;
    private String _naaccrXmlId;
    private String _table;
    private String _default;
    private List<StagingMetadata> _metadata;

    public StagingSchemaOutput() {
    }

    public StagingSchemaOutput(String key, String name) {
        setKey(key);
        setName(name);
    }

    public StagingSchemaOutput(String key, String name, String table) {
        setKey(key);
        setName(name);
        setTable(table);
    }

    /**
     * Copy constructor
     * @param other other StagingSchemaInput
     */
    public StagingSchemaOutput(StagingSchemaOutput other) {
        setKey(other.getKey());
        setName(other.getName());
        setDescription(other.getDescription());
        setNaaccrItem(other.getNaaccrItem());
        setNaaccrXmlId(other.getNaaccrXmlId());
        setTable(other.getTable());
        setDefault(other.getDefault());
        if (other.getMetadata() != null)
            setMetadata(new ArrayList<>(other.getMetadata()));
    }

    @JsonProperty("key")
    public String getKey() {
        return _key;
    }

    public void setKey(String key) {
        _key = key;
    }

    @JsonProperty("name")
    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    @JsonProperty("description")
    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    @JsonProperty("naaccr_item")
    public Integer getNaaccrItem() {
        return _naaccrItem;
    }

    public void setNaaccrItem(Integer naaccrItem) {
        _naaccrItem = naaccrItem;
    }

    @JsonProperty("naaccr_xml_id")
    public String getNaaccrXmlId() {
        return _naaccrXmlId;
    }

    public void setNaaccrXmlId(String naaccrXmlId) {
        _naaccrXmlId = naaccrXmlId;
    }

    @JsonProperty("table")
    public String getTable() {
        return _table;
    }

    public void setTable(String table) {
        _table = table;
    }

    @JsonProperty("default")
    public String getDefault() {
        return _default;
    }

    public void setDefault(String aDefault) {
        _default = aDefault;
    }

    @JsonProperty("metadata")
    public List<StagingMetadata> getMetadata() {
        return _metadata;
    }

    public void setMetadata(List<StagingMetadata> metadata) {
        _metadata = metadata;
    }

}

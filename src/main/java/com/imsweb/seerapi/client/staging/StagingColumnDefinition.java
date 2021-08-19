/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"key", "name", "type", "source"})
public class StagingColumnDefinition {

    private String _key;
    private String _name;
    private ColumnType _type;
    private String _source;

    enum ColumnType {
        INPUT,
        DESCRIPTION,
        ENDPOINT
    }

    public StagingColumnDefinition() {
    }

    /**
     * Constructor
     * @param key input key
     * @param name column name
     * @param type column type
     */
    public StagingColumnDefinition(String key, String name, ColumnType type) {
        setKey(key);
        setName(name);
        setType(type);
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

    @JsonProperty("type")
    public ColumnType getType() {
        return _type;
    }

    public void setType(ColumnType type) {
        _type = type;
    }

    @JsonProperty("source")
    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }
}

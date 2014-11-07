/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

import org.codehaus.jackson.annotate.JsonProperty;

public class StagingColumnDefinition {

    private String _key;
    private String _name;
    private ColumnType _type;

    /**
     * Morphia requires a default constructor
     */
    public StagingColumnDefinition() {
    }

    /**
     * Constructor
     * @param key
     * @param name
     * @param type
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

    enum ColumnType {
        INPUT,
        DESCRIPTION,
        ENDPOINT
    }

}

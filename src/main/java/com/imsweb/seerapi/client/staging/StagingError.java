/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * An error object
 */
@JsonPropertyOrder({"type", "table", "columns", "key", "message"})
public class StagingError {

    private Type _type;
    private String _table;
    private List<String> _columns;
    private String _key;
    private String _message;

    /**
     * Default constructor (required for Jackson)
     */
    public StagingError() {
    }

    /**
     * Constructor
     * @param type type of error
     */
    public StagingError(Type type) {
        setType(type);
    }

    @JsonProperty("type")
    public Type getType() {
        return _type;
    }

    public void setType(Type type) {
        _type = type;
    }

    @JsonProperty("table")
    public String getTable() {
        return _table;
    }

    public void setTable(String table) {
        _table = table;
    }

    @JsonProperty("columns")
    public List<String> getColumns() {
        return _columns;
    }

    public void setColumns(List<String> columns) {
        _columns = columns;
    }

    @JsonProperty("key")
    public String getKey() {
        return _key;
    }

    public void setKey(String key) {
        _key = key;
    }

    @JsonProperty("message")
    public String getMessage() {
        return _message;
    }

    public void setMessage(String message) {
        _message = message;
    }

    public enum Type {
        // an input key was supplied that is not defined in the input definition
        UNKNOWN_INPUT,

        // a required input value was not contained in the input definition table
        INVALID_REQUIRED_INPUT,

        // a non-required input value was not contained in the input definition table
        INVALID_NON_REQUIRED_INPUT,

        // an input mapping from value did not exist
        UNKNOWN_INPUT_MAPPING,

        // an ERROR endpoint was hit during staging processing
        STAGING_ERROR,

        // a table was processed during staging and no match was found
        MATCH_NOT_FOUND,

        // a specified table does not exist
        UNKNOWN_TABLE,

        // processing a table ended up in an infinite loop due to JUMPs
        INFINITE_LOOP,

        // an output value was produced which was not contained in the output definition table
        INVALID_OUTPUT
    }

}

/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * An error object
 */
@JsonPropertyOrder({"table", "key", "message"})
public class StagingError {

    private String _table;
    private String _key;
    private String _message;

    /**
     * Default constructor
     */
    public StagingError() {
    }

    @JsonProperty("table")
    public String getTable() {
        return _table;
    }

    public void setTable(String table) {
        _table = table;
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

    /**
     * Build class for Error
     */
    public static class ErrorBuilder {

        private StagingError _error;

        public ErrorBuilder(String message) {
            _error = new StagingError();
            _error.setMessage(message);
        }

        public ErrorBuilder table(String table) {
            _error.setTable(table);
            return this;
        }

        public ErrorBuilder key(String key) {
            _error.setKey(key);
            return this;
        }

        public ErrorBuilder message(String message) {
            _error.setMessage(message);
            return this;
        }

        public StagingError build() {
            return _error;
        }
    }
}

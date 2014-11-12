/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

/**
 * An error object
 */
public class Error {

    private String _table;
    private String _key;
    private String _message;

    /**
     * Default constructor
     */
    public Error() {
    }

    public String getTable() {
        return _table;
    }

    public void setTable(String table) {
        _table = table;
    }

    public String getKey() {
        return _key;
    }

    public void setKey(String key) {
        _key = key;
    }

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

        private Error _error;

        public ErrorBuilder(String message) {
            _error = new Error();
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

        public Error build() {
            return _error;
        }
    }
}

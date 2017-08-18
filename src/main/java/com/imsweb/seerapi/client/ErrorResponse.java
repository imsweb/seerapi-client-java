package com.imsweb.seerapi.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {

    @JsonProperty("timestamp")
    private String _timestamp;
    @JsonProperty("status")
    private Integer _status;
    @JsonProperty("error")
    private String _error;
    @JsonProperty("message")
    private String _message;
    @JsonProperty("path")
    private String _path;

    public String getTimestamp() {
        return _timestamp;
    }

    public void setTimestamp(String timestamp) {
        _timestamp = timestamp;
    }

    public Integer getStatus() {
        return _status;
    }

    public void setStatus(Integer status) {
        _status = status;
    }

    public String getError() {
        return _error;
    }

    public void setError(String error) {
        _error = error;
    }

    public String getMessage() {
        return _message;
    }

    public void setMessage(String message) {
        _message = message;
    }

    public String getPath() {
        return _path;
    }

    public void setPath(String path) {
        _path = path;
    }
}

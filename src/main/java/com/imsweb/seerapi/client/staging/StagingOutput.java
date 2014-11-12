/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * A wrapper class for the decision engine Result class.  It exists to add the JSON mappings.
 */
public class StagingOutput {

    Map<String, String> _output;
    Map<String, String> _input;
    List<String> _path;
    List<Error> _errors;

    /**
     * Default Constructor
     */
    public StagingOutput() {

    }

    @JsonProperty("output")
    public Map<String, String> getOutput() {
        return _output;
    }

    public void setOutput(Map<String, String> output) {
        _output = output;
    }

    @JsonIgnore
    public String getOutput(String key) {
        return getOutput().get(key);
    }

    @JsonProperty("table_path")
    public List<String> getPath() {
        return _path;
    }

    public void setPath(List<String> path) {
        _path = path;
    }

    @JsonProperty("errors")
    public List<Error> getErrors() {
        return _errors;
    }

    public void setErrors(List<Error> errors) {
        _errors = errors;
    }

    @JsonProperty("input")
    public Map<String, String> getInput() {
        return _input;
    }

    public void setInput(Map<String, String> input) {
        _input = input;
    }
}

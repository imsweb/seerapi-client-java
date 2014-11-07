/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * A wrapper class for the decision engine Result class.  It exists to add the JSON mappings.
 */
public class StagingOutput {

    Result _result;
    Map<String, String> _input;

    /**
     * Constructor
     * @param result
     */
    public StagingOutput(Result result) {
        _result = result;
    }

    @JsonProperty("output")
    public Map<String, String> getOutput() {
        return _result.getContext();
    }

    @JsonProperty("table_path")
    public List<String> getPath() {
        return _result.getPath();
    }

    @JsonProperty("errors")
    public List<Error> getErrors() {
        return _result.getErrors();
    }

    @JsonProperty("input")
    public Map<String, String> getInput() {
        return _input;
    }

    public void setInput(Map<String, String> input) {
        _input = input;
    }
}

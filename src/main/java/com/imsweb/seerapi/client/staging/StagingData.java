/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonPropertyOrder({"input", "output", "errors", "path"})
public class StagingData {

    // key definitions
    public static final String PRIMARY_SITE_KEY = "site";
    public static final String HISTOLOGY_KEY = "hist";

    private Map<String, String> _input = new HashMap<String, String>();
    private Map<String, String> _output = new HashMap<String, String>();
    private List<StagingError> _errors = new ArrayList<StagingError>();
    private List<String> _path = new ArrayList<String>();

    /**
     * Default constructor
     */
    public StagingData() {
    }

    /**
     * Construct with input map
     * @param input input map
     */
    public StagingData(Map<String, String> input) {
        _input = input;
    }

    /**
     * Construct with site/histology
     * @param site primary site
     * @param hist histology
     */
    public StagingData(String site, String hist) {
        setInput(PRIMARY_SITE_KEY, site);
        setInput(HISTOLOGY_KEY, hist);
    }

    @JsonProperty("input")
    public Map<String, String> getInput() {
        return _input;
    }

    @JsonIgnore
    public String getInput(String key) {
        return _input.get(key);
    }

    public void setInput(String key, String value) {
        _input.put(key, value);
    }

    // output getters

    @JsonProperty("output")
    public Map<String, String> getOutput() {
        return _output;
    }

    @JsonIgnore
    public String getOutput(String key) {
        return _output.get(key);
    }

    public void setOutput(Map<String, String> output) {
        _output = output;
    }

    // errors

    @JsonProperty("errors")
    public List<StagingError> getErrors() {
        return _errors;
    }

    public void setErrors(List<StagingError> errors) {
        _errors = errors;
    }

    // path

    @JsonProperty("path")
    public List<String> getPath() {
        return _path;
    }

    public void setPath(List<String> path) {
        _path = path;
    }

}

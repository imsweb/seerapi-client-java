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

public class StagingTableRow {

    private Map<String, List<StagingStringRange>> _inputs = new HashMap<>();
    private List<StagingEndpoint> _endpoints = new ArrayList<>();

    @JsonIgnore
    public List<StagingStringRange> getColumnInput(String key) {
        return _inputs.get(key);
    }

    @JsonProperty("inputs")
    public Map<String, List<StagingStringRange>> getInputs() {
        return _inputs;
    }

    /**
     * Add a single columns input list
     * @param key
     * @param range
     */
    public void addInput(String key, List<StagingStringRange> range) {
        _inputs.put(key, range);
    }

    public void setInputs(Map<String, List<StagingStringRange>> inputs) {
        _inputs = inputs;
    }

    @JsonProperty("endpoint")
    public List<StagingEndpoint> getEndpoints() {
        return _endpoints;
    }

    public void setEndpoints(List<StagingEndpoint> endpoints) {
        _endpoints = endpoints;
    }

    public void addEndpoint(StagingEndpoint endpoint) {
        _endpoints.add(endpoint);
    }
}

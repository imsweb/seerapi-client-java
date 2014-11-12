/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

import java.util.List;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonProperty;

public class StagingTablePath {

    private String _id;
    private List<StagingKeyMapping> _inputMapping;
    private List<StagingKeyMapping> _outputMapping;
    private Set<String> _inputs;
    private Set<String> _outputs;

    /**
     * Morphia requires a default constructor
     */
    public StagingTablePath() {
    }

    public StagingTablePath(String id) {
        setId(id);
    }

    @JsonProperty("id")
    public String getId() {
        return _id;
    }

    public void setId(String id) {
        _id = id;
    }

    @JsonProperty("input_mapping")
    public List<StagingKeyMapping> getInputMapping() {
        return _inputMapping;
    }

    public void setInputMapping(List<StagingKeyMapping> input) {
        _inputMapping = input;
    }

    @JsonProperty("output_mapping")
    public List<StagingKeyMapping> getOutputMapping() {
        return _outputMapping;
    }

    public void setOutputMapping(List<StagingKeyMapping> output) {
        _outputMapping = output;
    }

    @JsonProperty("inputs")
    public Set<String> getInputs() {
        return _inputs;
    }

    public void setInputs(Set<String> inputs) {
        _inputs = inputs;
    }

    @JsonProperty("outputs")
    public Set<String> getOutputs() {
        return _outputs;
    }

    public void setOutputs(Set<String> outputs) {
        _outputs = outputs;
    }
}

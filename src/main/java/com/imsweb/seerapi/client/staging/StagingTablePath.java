/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

import java.util.LinkedHashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

@JsonPropertyOrder({"id", "input_mapping", "output_mapping", "inputs", "outputs"})
public class StagingTablePath {

    private String _id;
    private Set<StagingKeyMapping> _inputMapping;
    private Set<StagingKeyMapping> _outputMapping;
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
    public Set<StagingKeyMapping> getInputMapping() {
        return _inputMapping;
    }

    @JsonDeserialize(as = LinkedHashSet.class)
    public void setInputMapping(Set<StagingKeyMapping> input) {
        _inputMapping = input;
    }

    @JsonProperty("output_mapping")
    public Set<StagingKeyMapping> getOutputMapping() {
        return _outputMapping;
    }

    @JsonDeserialize(as = LinkedHashSet.class)
    public void setOutputMapping(Set<StagingKeyMapping> output) {
        _outputMapping = output;
    }

    @JsonProperty("inputs")
    public Set<String> getInputs() {
        return _inputs;
    }

    @JsonDeserialize(as = LinkedHashSet.class)
    public void setInputs(Set<String> inputs) {
        _inputs = inputs;
    }

    @JsonProperty("outputs")
    public Set<String> getOutputs() {
        return _outputs;
    }

    @JsonDeserialize(as = LinkedHashSet.class)
    public void setOutputs(Set<String> outputs) {
        _outputs = outputs;
    }
}

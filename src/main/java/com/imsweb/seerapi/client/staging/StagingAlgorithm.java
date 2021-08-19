/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"algorithm", "name", "description"})
public class StagingAlgorithm {

    private String _algorithm;
    private String _name;
    private String _description;

    public StagingAlgorithm() {
    }

    public StagingAlgorithm(String algorithm) {
        setAlgorithm(algorithm);
    }

    @JsonProperty("algorithm")
    public String getAlgorithm() {
        return _algorithm;
    }

    public void setAlgorithm(String algorithm) {
        _algorithm = algorithm;
    }

    @JsonProperty("name")
    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    @JsonProperty("description")
    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

}

/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "name", "title", "schema_num", "discriminators", "staging_inputs", "staging_outputs"})
public class StagingSchemaInfo {

    private String _id;
    private String _name;
    private String _title;
    private Integer _schemaNum;
    private List<StagingSchemaInput> _discriminators;
    private Set<String> _stagingInputs;
    private Set<String> _stagingOutputs;

    /**
     * Default constructor
     */
    public StagingSchemaInfo() {
    }

    /**
     * Constructor
     * @param schema
     */
    public StagingSchemaInfo(StagingSchema schema) {
        setId(schema.getId());
        setName(schema.getName());
        setTitle(schema.getTitle());
        setSchemaNum(schema.getSchemaNum());
    }

    @JsonProperty("id")
    public String getId() {
        return _id;
    }

    public void setId(String id) {
        _id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    @JsonProperty("title")
    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    @JsonProperty("schema_num")
    public Integer getSchemaNum() {
        return _schemaNum;
    }

    public void setSchemaNum(Integer schemaNum) {
        _schemaNum = schemaNum;
    }

    @JsonProperty("discriminators")
    public List<StagingSchemaInput> getDiscriminators() {
        return _discriminators;
    }

    public void setDiscriminators(List<StagingSchemaInput> discriminators) {
        _discriminators = discriminators;
    }

    @JsonProperty("staging_inputs")
    public Set<String> getStagingInputs() {
        return _stagingInputs;
    }

    public void setStagingInputs(Set<String> stagingInputs) {
        _stagingInputs = stagingInputs;
    }

    @JsonProperty("staging_outputs")
    public Set<String> getStagingOutputs() {
        return _stagingOutputs;
    }

    public void setStagingOutputs(Set<String> stagingOutputs) {
        _stagingOutputs = stagingOutputs;
    }
}

/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class StagingSchemaInput {

    // agencies
    public enum Agency {
        SEER,
        AJCC,
        NPCR,
        COC
    }

    private String _key;
    private String _name;
    private Integer _naaccrItem;
    List<Agency> _agencyRequirement;
    private String _values;
    private String _default;
    private String _table;

    /**
     * Morphia requires a default constructor
     */
    public StagingSchemaInput() {
    }

    public StagingSchemaInput(String key, String name) {
        setKey(key);
        setName(name);
    }

    public StagingSchemaInput(String key, String name, String table) {
        setKey(key);
        setName(name);
        setTable(table);
    }

    /**
     * Copy constructor
     * @param other
     */
    public StagingSchemaInput(StagingSchemaInput other) {
        setKey(other.getKey());
        setName(other.getName());
        setNaaccrItem(other.getNaaccrItem());
        if (other.getAgencyRequirement() != null)
            setAgencyRequirement(new ArrayList<>(other.getAgencyRequirement()));
        setRawValues(other.getRawValues());
        setDefault(other.getDefault());
        setTable(other.getTable());
        setRawValues(other.getRawValues());
    }

    @JsonProperty("key")
    public String getKey() {
        return _key;
    }

    public void setKey(String key) {
        _key = key;
    }

    @JsonProperty("name")
    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    @JsonProperty("naaccr_item")
    public Integer getNaaccrItem() {
        return _naaccrItem;
    }

    public void setNaaccrItem(Integer naaccrItem) {
        _naaccrItem = naaccrItem;
    }

    @JsonProperty("agency_requirement")
    public List<Agency> getAgencyRequirement() {
        return _agencyRequirement;
    }

    public void setAgencyRequirement(List<Agency> agencyRequirement) {
        _agencyRequirement = agencyRequirement;
    }

    @JsonProperty("values")
    public String getRawValues() {
        return _values;
    }

    public void setRawValues(String values) {
        _values = values;
    }

    @JsonProperty("default")
    public String getDefault() {
        return _default;
    }

    public void setDefault(String aDefault) {
        _default = aDefault;
    }

    @JsonProperty("table")
    public String getTable() {
        return _table;
    }

    public void setTable(String table) {
        _table = table;
    }

}

/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

@JsonPropertyOrder({"key", "name", "naaccr_item", "values", "default", "table", "used_for_staging", "unit", "decimal_places", "metadata"})
public class StagingSchemaInput {

    private String _key;
    private String _name;
    private Integer _naaccrItem;
    private String _values;
    private String _default;
    private String _table;
    private Boolean _usedForStaging;
    private String _unit;
    private Integer _decimalPlaces;
    private Set<String> _metadata;

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
     * @param other other StagingSchemaInput
     */
    public StagingSchemaInput(StagingSchemaInput other) {
        setKey(other.getKey());
        setName(other.getName());
        setNaaccrItem(other.getNaaccrItem());
        setRawValues(other.getRawValues());
        setDefault(other.getDefault());
        setTable(other.getTable());
        if (other.getMetadata() != null)
            setMetadata(new HashSet<String>(other.getMetadata()));
        setUsedForStaging(other.getUsedForStaging());
        setUnit(other.getUnit());
        setDecimalPlaces(other.getDecimalPlaces());
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

    @JsonProperty("used_for_staging")
    public Boolean getUsedForStaging() {
        return _usedForStaging;
    }

    public void setUsedForStaging(Boolean usedForStaging) {
        _usedForStaging = usedForStaging;
    }

    @JsonProperty("decimal_places")
    public Integer getDecimalPlaces() {
        return _decimalPlaces;
    }

    public void setDecimalPlaces(Integer decimalPlaces) {
        _decimalPlaces = decimalPlaces;
    }

    @JsonProperty("unit")
    public String getUnit() {
        return _unit;
    }

    public void setUnit(String unit) {
        _unit = unit;
    }

    @JsonProperty("metadata")
    public Set<String> getMetadata() {
        return _metadata;
    }

    @JsonDeserialize(as = LinkedHashSet.class)
    public void setMetadata(Set<String> metadata) {
        _metadata = metadata;
    }

}
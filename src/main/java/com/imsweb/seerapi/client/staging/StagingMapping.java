/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonPropertyOrder({"id", "name", "inclusion_tables", "exclusion_tables", "initial_context", "tables"})
public class StagingMapping {

    private String _id;
    private String _name;
    private List<StagingTablePath> _inclusionTables;
    private List<StagingTablePath> _exclusionTables;
    private Set<StagingKeyValue> _initialContext;
    private List<StagingTablePath> _tablePaths;

    /**
     * Default constructor
     */
    public StagingMapping() {
    }

    /**
     * Constructs with a name and title
     * @param id identifier
     * @param name name
     */
    public StagingMapping(String id, String name) {
        setId(id);
        setName(name);
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

    @JsonProperty("inclusion_tables")
    public List<StagingTablePath> getInclusionTables() {
        return _inclusionTables;
    }

    public void setInclusionTables(List<StagingTablePath> inclusionTables) {
        _inclusionTables = inclusionTables;
    }

    @JsonProperty("exclusion_tables")
    public List<StagingTablePath> getExclusionTables() {
        return _exclusionTables;
    }

    public void setExclusionTables(List<StagingTablePath> exclusionTables) {
        _exclusionTables = exclusionTables;
    }

    @JsonProperty("initial_context")
    public Set<StagingKeyValue> getInitialContext() {
        return _initialContext;
    }

    @JsonDeserialize(as = LinkedHashSet.class)
    public void setInitialContext(Set<StagingKeyValue> initialContext) {
        _initialContext = initialContext;
    }

    public void addInitialContext(String key, String value) {
        if (_initialContext == null)
            _initialContext = new HashSet<>();

        _initialContext.add(new StagingKeyValue(key, value));
    }

    @JsonProperty("tables")
    public List<StagingTablePath> getTablePaths() {
        return _tablePaths;
    }

    public void setTablePaths(List<StagingTablePath> tablePaths) {
        _tablePaths = tablePaths;
    }
}

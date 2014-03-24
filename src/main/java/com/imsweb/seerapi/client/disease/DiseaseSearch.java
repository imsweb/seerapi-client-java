/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.disease;

public class DiseaseSearch {

    public enum OutputType {
        FULL,
        PARTIAL,
        MIN
    }

    public enum SearchMode {
        OR,
        AND
    }

    private String _query;
    private Disease.Type _type;
    private String _siteCategory;
    private SearchMode _mode;
    private String _status;
    private String _assignedTo;
    private String _modifiedFrom;
    private String _modifiedTo;
    private String _publishedFrom;
    private String _publishedTo;
    private Boolean _beenPublished;
    private Boolean _hidden;
    private Integer _count;
    private Boolean _countOnly;
    private Boolean _includeGlossary;
    private OutputType _outputType;

    public String getQuery() {
        return _query;
    }

    public void setQuery(String query) {
        _query = query;
    }

    public Disease.Type getType() {
        return _type;
    }

    public void setType(Disease.Type type) {
        _type = type;
    }

    public String getSiteCategory() {
        return _siteCategory;
    }

    public void setSiteCategory(String siteCategory) {
        _siteCategory = siteCategory;
    }

    public SearchMode getMode() {
        return _mode;
    }

    public void setMode(SearchMode mode) {
        _mode = mode;
    }

    public String getStatus() {
        return _status;
    }

    public void setStatus(String status) {
        _status = status;
    }

    public String getAssignedTo() {
        return _assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        _assignedTo = assignedTo;
    }

    public String getModifiedFrom() {
        return _modifiedFrom;
    }

    public void setModifiedFrom(String modifiedFrom) {
        _modifiedFrom = modifiedFrom;
    }

    public String getModifiedTo() {
        return _modifiedTo;
    }

    public void setModifiedTo(String modifiedTo) {
        _modifiedTo = modifiedTo;
    }

    public String getPublishedFrom() {
        return _publishedFrom;
    }

    public void setPublishedFrom(String publishedFrom) {
        _publishedFrom = publishedFrom;
    }

    public String getPublishedTo() {
        return _publishedTo;
    }

    public void setPublishedTo(String publishedTo) {
        _publishedTo = publishedTo;
    }

    public Boolean getBeenPublished() {
        return _beenPublished;
    }

    public void setBeenPublished(Boolean beenPublished) {
        _beenPublished = beenPublished;
    }

    public Boolean getHidden() {
        return _hidden;
    }

    public void setHidden(Boolean hidden) {
        _hidden = hidden;
    }

    public Integer getCount() {
        return _count;
    }

    public void setCount(Integer count) {
        _count = count;
    }

    public Boolean getCountOnly() {
        return _countOnly;
    }

    public void setCountOnly(Boolean countOnly) {
        _countOnly = countOnly;
    }

    public Boolean getIncludeGlossary() {
        return _includeGlossary;
    }

    public void setIncludeGlossary(Boolean includeGlossary) {
        _includeGlossary = includeGlossary;
    }

    public OutputType getOutputType() {
        return _outputType;
    }

    public void setOutputType(OutputType outputType) {
        _outputType = outputType;
    }
}

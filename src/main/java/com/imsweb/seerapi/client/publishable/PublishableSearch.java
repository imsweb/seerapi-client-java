/*
 * Copyright (C) 2012 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.publishable;

import java.util.HashMap;
import java.util.Map;

public class PublishableSearch {

    public enum OutputType {
        FULL,
        PARTIAL,
        MIN
    }

    public enum SearchMode {
        OR,
        AND
    }

    /**
     * Shared search parameters
     */
    private String _query;
    private SearchMode _mode;
    private String _status;
    private String _assignedTo;
    private String _modifiedFrom;
    private String _modifiedTo;
    private String _publishedFrom;
    private String _publishedTo;
    private Integer _count;
    private Integer _offset;
    private OutputType _outputType;
    private String _orderBy;

    public String getQuery() {
        return _query;
    }

    public void setQuery(String query) {
        _query = query;
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

    public Integer getCount() {
        return _count;
    }

    public void setCount(Integer count) {
        _count = count;
    }

    public Integer getOffset() {
        return _offset;
    }

    public void setOffset(Integer offset) {
        _offset = offset;
    }

    public OutputType getOutputType() {
        return _outputType;
    }

    public void setOutputType(OutputType outputType) {
        _outputType = outputType;
    }

    public String getOrderBy() {
        return _orderBy;
    }

    public void setOrderBy(String orderBy) {
        _orderBy = orderBy;
    }

    /**
     * Return a map of parameters to be used in the API calls
     * @return a Map of parameters
     */
    public Map<String, String> paramMap() {
        Map<String, String> params = new HashMap<>();

        if (getQuery() != null)
            params.put("q", getQuery());
        if (getMode() != null)
            params.put("mode", getMode().toString());
        if (getStatus() != null)
            params.put("status", getStatus());
        if (getAssignedTo() != null)
            params.put("assigned_to", getAssignedTo());
        if (getModifiedFrom() != null)
            params.put("modified_from", getModifiedFrom());
        if (getModifiedTo() != null)
            params.put("modified_to", getModifiedTo());
        if (getPublishedFrom() != null)
            params.put("published_from", getPublishedFrom());
        if (getPublishedTo() != null)
            params.put("published_to", getPublishedTo());
        if (getCount() != null)
            params.put("count", getCount().toString());
        if (getOffset() != null)
            params.put("offset", getOffset().toString());
        if (getOrderBy() != null)
            params.put("order", getOrderBy());
        if (getOutputType() != null)
            params.put("output_type", getOutputType().toString());

        return params;
    }
}

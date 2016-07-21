/*
 * Copyright (C) 2012 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.ndc;

import java.util.HashMap;
import java.util.Map;

public class NdcSearch {

    private String _query;
    private Boolean _includeRemoved;
    private String _addedSince;
    private String _modifiedSince;
    private String _removedSince;
    private Integer _page;
    private Integer _perPage;
    private String _order;

    public String getQuery() {
        return _query;
    }

    public void setQuery(String query) {
        _query = query;
    }

    public Boolean getIncludeRemoved() {
        return _includeRemoved;
    }

    public void setIncludeRemoved(Boolean includeRemoved) {
        this._includeRemoved = includeRemoved;
    }

    public String getAddedSince() {
        return _addedSince;
    }

    public void setAddedSince(String addedSince) {
        _addedSince = addedSince;
    }

    public String getModifiedSince() {
        return _modifiedSince;
    }

    public void setModifiedSince(String modifiedSince) {
        _modifiedSince = modifiedSince;
    }

    public String getRemovedSince() {
        return _removedSince;
    }

    public void setRemovedSince(String removedSince) {
        _removedSince = removedSince;
    }

    public Integer getPage() {
        return _page;
    }

    public void setPage(Integer page) {
        _page = page;
    }

    public Integer getPerPage() {
        return _perPage;
    }

    public void setPerPage(Integer perPage) {
        _perPage = perPage;
    }

    public String getOrder() {
        return _order;
    }

    public void setOrder(String orderBy) {
        _order = orderBy;
    }

    /**
     * Return a map of parameters to be used in the API calls
     * @return a Map of parameters
     */
    public Map<String, String> paramMap() {
        Map<String, String> params = new HashMap<>();

        if (getQuery() != null)
            params.put("q", getQuery());
        if (getIncludeRemoved() != null)
            params.put("include_removed", getIncludeRemoved() ? "true" : "false");
        if (getAddedSince() != null)
            params.put("added_since", getAddedSince());
        if (getModifiedSince() != null)
            params.put("modified_since", getModifiedSince());
        if (getRemovedSince() != null)
            params.put("removed_since", getRemovedSince());
        if (getPage() != null)
            params.put("page", getPage().toString());
        if (getPerPage() != null)
            params.put("per_page", getPerPage().toString());
        if (getOrder() != null)
            params.put("order", getOrder());

        return params;
    }
}

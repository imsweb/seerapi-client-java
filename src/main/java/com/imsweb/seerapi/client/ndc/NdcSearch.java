/*
 * Copyright (C) 2012 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.ndc;

import java.util.HashMap;
import java.util.Map;

import com.imsweb.seerapi.client.ndc.NdcSeerInfo.Category;

public class NdcSearch {

    private String _query;
    private Category _category;
    private Boolean _hasSeerInfo;
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

    public Category getCategory() {
        return _category;
    }

    public void setCategory(Category category) {
        _category = category;
    }

    public Boolean getHasSeerInfo() {
        return _hasSeerInfo;
    }

    public void setHasSeerInfo(Boolean hasSeerInfo) {
        _hasSeerInfo = hasSeerInfo;
    }

    public Boolean getIncludeRemoved() {
        return _includeRemoved;
    }

    public void setIncludeRemoved(Boolean includeRemoved) {
        _includeRemoved = includeRemoved;
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

    public void setOrder(String order) {
        _order = order;
    }

    /**
     * Return a map of parameters to be used in the API calls
     * @return a Map of parameters
     */
    public Map<String, String> paramMap() {
        Map<String, String> params = new HashMap<>();

        if (getQuery() != null)
            params.put("q", getQuery());
        if (getCategory() != null)
            params.put("category", getCategory().toString());
        if (getHasSeerInfo() != null)
            params.put("has_seer_info", getHasSeerInfo().toString());
        if (getIncludeRemoved() != null)
            params.put("include_removed", Boolean.TRUE.equals(getIncludeRemoved()) ? "true" : "false");
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

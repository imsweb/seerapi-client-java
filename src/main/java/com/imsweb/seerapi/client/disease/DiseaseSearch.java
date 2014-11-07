/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.disease;

import com.imsweb.seerapi.client.disease.Disease.Type;
import com.imsweb.seerapi.client.publishable.PublishableSearch;

public class DiseaseSearch extends PublishableSearch {

    /**
     * Disease-specific search parameters
     */
    private Type _type;
    private String _siteCategory;

    public DiseaseSearch() {
        super();
    }

    public DiseaseSearch(String query) {
        super();

        setQuery(query);
    }

    public DiseaseSearch(String query, Type type) {
        super();

        setQuery(query);
        setType(type);
    }

    public Type getType() {
        return _type;
    }

    public void setType(Type type) {
        _type = type;
    }

    public String getSiteCategory() {
        return _siteCategory;
    }

    public void setSiteCategory(String siteCategory) {
        _siteCategory = siteCategory;
    }
}

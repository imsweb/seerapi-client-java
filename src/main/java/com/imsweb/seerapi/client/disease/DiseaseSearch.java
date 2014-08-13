/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.disease;

import com.imsweb.seerapi.client.publishable.PublishableSearch;

public class DiseaseSearch extends PublishableSearch {

    /**
     * Disease-specific search parameters
     */
    private Disease.Type _type;
    private String _siteCategory;

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
}

/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.siterecode;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SiteRecode {

    @JsonProperty("site")
    protected String _site;
    @JsonProperty("hist")
    protected String _hist;
    @JsonProperty("site_group")
    protected String _siteGroup;

    public String getSite() {
        return _site;
    }

    public void setSite(String site) {
        _site = site;
    }

    public String getHist() {
        return _hist;
    }

    public void setHist(String hist) {
        _hist = hist;
    }

    public String getSiteGroup() {
        return _siteGroup;
    }

    public void setSiteGroup(String siteGroup) {
        _siteGroup = siteGroup;
    }
}

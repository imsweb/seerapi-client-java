package com.imsweb.seerapi.client.disease;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SiteCategory {

    @JsonProperty("id")
    protected String _id;
    @JsonProperty("label")
    protected String _label;
    @JsonProperty("sites")
    protected List<SiteRange> _sites;

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        _id = id;
    }

    public String getLabel() {
        return _label;
    }

    public void setLabel(String label) {
        _label = label;
    }

    public List<SiteRange> getSites() {
        return _sites;
    }

    public void setSites(List<SiteRange> sites) {
        _sites = sites;
    }
}

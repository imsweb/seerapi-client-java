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

    public String getLabel() {
        return _label;
    }

    public List<SiteRange> getSites() {
        return _sites;
    }
}

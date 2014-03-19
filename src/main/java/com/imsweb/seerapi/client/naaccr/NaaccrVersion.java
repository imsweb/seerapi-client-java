package com.imsweb.seerapi.client.naaccr;

import org.codehaus.jackson.annotate.JsonProperty;

public class NaaccrVersion {

    @JsonProperty("version")
    protected String _version;
    @JsonProperty("name")
    protected String _name;
    @JsonProperty("length")
    protected Integer _length;
    @JsonProperty("description")
    protected String _description;
    @JsonProperty("style")
    protected String _style;

    public String getVersion() {
        return _version;
    }

    public String getName() {
        return _name;
    }

    public Integer getLength() {
        return _length;
    }

    public String getDescription() {
        return _description;
    }

    public String getStyle() {
        return _style;
    }
}

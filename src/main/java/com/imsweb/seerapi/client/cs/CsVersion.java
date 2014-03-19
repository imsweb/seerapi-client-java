package com.imsweb.seerapi.client.cs;

import org.codehaus.jackson.annotate.JsonProperty;

public class CsVersion {

    @JsonProperty("version")
    protected String _version;
    @JsonProperty("timestamp")
    protected String _timestamp;
    @JsonProperty("num_schemas")
    protected Integer _numSchemas;

    public String getVersion() {
        return _version;
    }

    public String getTimestamp() {
        return _timestamp;
    }

    public Integer getNumSchemas() {
        return _numSchemas;
    }
}

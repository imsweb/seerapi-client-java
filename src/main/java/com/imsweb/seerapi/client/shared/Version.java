package com.imsweb.seerapi.client.shared;

import org.codehaus.jackson.annotate.JsonProperty;

public class Version {

    @JsonProperty("version")
    protected String _version;
    @JsonProperty("count")
    protected Long _count;

    public String getVersion() {
        return _version;
    }

    public Long getCount() {
        return _count;
    }
}

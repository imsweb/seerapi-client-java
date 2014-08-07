package com.imsweb.seerapi.client.shared;

import org.codehaus.jackson.annotate.JsonProperty;

public class Version {

    @JsonProperty("version")
    protected String _version;
    @JsonProperty("count")
    protected Long _count;

    /**
     * Returns the version
     * @return version number
     */
    public String getVersion() {
        return _version;
    }

    /**
     * Returns the count
     * @return count
     */
    public Long getCount() {
        return _count;
    }
}

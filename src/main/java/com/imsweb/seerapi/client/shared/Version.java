package com.imsweb.seerapi.client.shared;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    public void setVersion(String version) {
        _version = version;
    }

    /**
     * Returns the count
     * @return count
     */
    public Long getCount() {
        return _count;
    }

    public void setCount(Long count) {
        _count = count;
    }
}

/*
 * Copyright (C) 2013 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.disease;

import org.codehaus.jackson.annotate.JsonProperty;

public class SiteRange {

    @JsonProperty("low")
    protected String _low;
    @JsonProperty("high")
    protected String _high;

    public SiteRange() {
    }

    public SiteRange(String low, String high) {
        _low = low;
        _high = high;
    }

    public String getLow() {
        return _low;
    }

    public String getHigh() {
        return _high;
    }
}

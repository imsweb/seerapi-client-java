/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Range {

    @JsonProperty("low")
    protected String _lowValue;
    @JsonProperty("high")
    protected String _highValue;

    public Range(String low, String high) {
        setLowValue(low);
        setHighValue(high);
    }

    public String getLowValue() {
        return _lowValue;
    }

    public void setLowValue(String lowValue) {
        _lowValue = lowValue;
    }

    public String getHighValue() {
        return _highValue;
    }

    public void setHighValue(String highValue) {
        _highValue = highValue;
    }

}

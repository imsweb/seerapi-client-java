/*
 * Copyright (C) 2013 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.disease;

import org.codehaus.jackson.annotate.JsonProperty;

public class YearRangeInteger extends YearRange {

    @JsonProperty("value")
    protected Integer _value;

    public Integer getValue() {
        return _value;
    }

    public void setValue(Integer value) {
        _value = value;
    }
}

/*
 * Copyright (C) 2013 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.disease;

import com.fasterxml.jackson.annotation.JsonProperty;

public class YearRangeString extends YearRange {

    @JsonProperty("value")
    protected String _value;

    public String getValue() {
        return _value;
    }
}

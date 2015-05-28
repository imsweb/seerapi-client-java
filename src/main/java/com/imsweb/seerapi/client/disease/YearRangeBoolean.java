/*
 * Copyright (C) 2013 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.disease;

import com.fasterxml.jackson.annotation.JsonProperty;

public class YearRangeBoolean extends YearRange {

    @JsonProperty("value")
    protected Boolean _value;

    public Boolean getValue() {
        return _value;
    }
}
